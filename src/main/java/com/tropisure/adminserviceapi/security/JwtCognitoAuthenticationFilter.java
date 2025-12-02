package com.tropisure.adminserviceapi.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.util.DefaultResourceRetriever;
import com.nimbusds.jose.util.Resource;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtCognitoAuthenticationFilter implements Filter {

    @Value("${aws.cognito.user-pool-id}")
    private String userPoolId;

    @Value("${aws.region}")
    private String region;

    private volatile Map<String, RSAKey> publicKeys = new HashMap<>();

    private void loadJWKS() throws Exception {
        if (!publicKeys.isEmpty()) return;

        String jwksUrl = String.format(
                "https://cognito-idp.%s.amazonaws.com/%s/.well-known/jwks.json",
                region, userPoolId
        );

        DefaultResourceRetriever retriever = new DefaultResourceRetriever(3000, 3000);
        Resource resource = retriever.retrieveResource(new URL(jwksUrl));
        JWKSet jwkSet = JWKSet.parse(resource.getContent());

        Map<String, RSAKey> loadedKeys = new HashMap<>();
        jwkSet.getKeys().forEach(k -> {
            if (k instanceof RSAKey rsaKey && rsaKey.getKeyID() != null) {
                loadedKeys.put(rsaKey.getKeyID(), rsaKey);
            }
        });

        publicKeys = loadedKeys;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        try {
            loadJWKS();
        } catch (Exception e) {
            chain.doFilter(request, response);
            return;
        }

        HttpServletRequest httpReq = (HttpServletRequest) request;
        String authHeader = httpReq.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try {
            SignedJWT jwt = SignedJWT.parse(token);

            // Validate issuer
            String expectedIssuer = String.format("https://cognito-idp.%s.amazonaws.com/%s", region, userPoolId);
            String actualIssuer = jwt.getJWTClaimsSet().getIssuer();
            if (!expectedIssuer.equals(actualIssuer)) {
                throw new SecurityException("Invalid issuer");
            }

            // Get kid
            String kid = jwt.getHeader().getKeyID();
            RSAKey rsaKey = publicKeys.get(kid);

            if (rsaKey == null) {
                throw new SecurityException("Unable to find RSA key for KID: " + kid);
            }

            // Validate signature
            if (!jwt.verify(new RSASSAVerifier(rsaKey))) {
                throw new SecurityException("Invalid token signature");
            }

            // Extract claims
            String username = jwt.getJWTClaimsSet().getSubject();
            List<String> groups = Optional.ofNullable(
                    jwt.getJWTClaimsSet().getStringListClaim("cognito:groups")
            ).orElse(List.of());

            List<SimpleGrantedAuthority> authorities =
                    groups.stream()
                            .map(g -> new SimpleGrantedAuthority("ROLE_" + g))
                            .collect(Collectors.toList());

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(auth);

        } catch (ParseException | JOSEException | SecurityException ex) {
            HttpServletResponse httpResp = (HttpServletResponse) response;
            httpResp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResp.getWriter().write("Unauthorized: " + ex.getMessage());
            return;
        }

        chain.doFilter(request, response);
    }
}
