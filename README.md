# Tropisure Admin Service API

The **Tropisure Admin Service API** is a Spring Boot--based microservice
responsible for managing MGA (Managing General Agent) profiles, Carrier
profiles, Audit Logs, and system-level administrative functions.

This service supports:

-   JWT authentication using AWS Cognito (prod)
-   Mock authentication (local)
-   Audit event logging
-   PostgreSQL persistence
-   Email notifications via AWS SES (prod)

## 🚀 Tech Stack

  Component        Technology
  ---------------- -------------------------------
  - **Language**:         Java 21
  - **Framework**:        Spring Boot 3.4.x
  - **Build Tool**:       Maven
  - **Security**:         Spring Security + Cognito JWT
  - **Database**:         PostgreSQL 15+
  - **ORM**:              Hibernate / JPA
  - **Cloud Services**:   AWS Cognito, AWS SES
  - **Local Profile**:    Mock JWT + In-Memory SES
 
## 📁 Project Structure

    src/main/java/com/tropisure/adminserviceapi
     ├── config
     ├── controller
     ├── dto
     ├── entity
     ├── repository
     ├── security
     ├── service
     └── util

## 🔧 Local vs Production Profiles

YAML-based Spring Profiles:

### application.yaml

``` yaml
spring:
  application:
    name: admin-service-api

  config:
    import:
      - application-local.yaml
      - application-prod.yaml
```

### application-local.yaml (default for developers)

``` yaml
spring:
  config:
    activate:
      on-profile: local

tropisure:
  security:
    mode: mock   # no Cognito required
```

### application-prod.yaml

``` yaml
spring:
  config:
    activate:
      on-profile: prod

tropisure:
  security:
    mode: cognito
  cognito:
    jwksUri: https://cognito-idp.<region>.amazonaws.com/<pool-id>/.well-known/jwks.json
    issuer: https://cognito-idp.<region>.amazonaws.com/<pool-id>
```

## 🏃 Running Locally

Runs using mock authentication (no Cognito or SES needed):

``` bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

Mock authentication automatically injects:

-   username: local-superadmin
-   authorities: ROLE_SUPERADMIN

## 🚀 Running in Production

``` bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

Requires:

-   AWS Cognito User Pool
-   Valid JWT access tokens
-   AWS SES configured credentials

## 🔐 Authentication

### Local Mode (mock)

No JWT token required.

A mock filter injects:

    SUPERADMIN (mock)

### Production Mode

Uses:

-   JwtCognitoAuthenticationFilter
-   Nimbus JOSE
-   JWK auto-refresh

JWT must include:

-   iss = Cognito Issuer\
-   token_use = access\
-   group = SUPERADMIN

## 📌 API Overview

### POST /api/admin/mga

Create a new MGA profile.

Example:

``` json
{
  "legalName": "ABC MGA LLC",
  "contactEmail": "mga.admin@example.com",
  "contactPhone": "+1-651-222-3355",
  "address": "123 Market Street",
  "contactName": "John Doe"
}
```

### POST /api/admin/carrier

Create a new carrier profile.

Example:

``` json
{
  "carrierName": "SecureLife Insurance Co",
  "contactEmail": "support@securelife.com",
  "contactPhone": "800-222-9988",
  "address": "7800 Chicago Ave",
  "contactName": "Sarah Miles"
}
```

### GET /api/admin/audit

Returns a list of audit logs.

## 📄 License

Proprietary --- Tropisure Internal Use Only.
