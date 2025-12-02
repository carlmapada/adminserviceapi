package com.tropisure.adminserviceapi.config;

import com.tropisure.adminserviceapi.security.JwtCognitoAuthenticationFilter;
import com.tropisure.adminserviceapi.mocks.MockAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.HeaderWriterFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtCognitoAuthenticationFilter jwtFilter;
    private final MockAuthFilter mockFilter;

    @Value("${security.mode:prod}")
    private String securityMode;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/admin/**").hasRole("SUPERADMIN")
                        .anyRequest().permitAll()
                );

        if (securityMode.equals("mock")) {
            // LOCAL TESTING → no Cognito required
            http.addFilterBefore(mockFilter, HeaderWriterFilter.class);
        } else {
            // PROD → real JWT validation
            http.addFilterBefore(jwtFilter, HeaderWriterFilter.class);
        }

        return http.build();
    }
}


