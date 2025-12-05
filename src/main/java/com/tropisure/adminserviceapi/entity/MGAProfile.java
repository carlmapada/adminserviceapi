package com.tropisure.adminserviceapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "mga_profile", schema = "registration_db")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MGAProfile {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "cognito_sub", unique = true)
    private String cognitoSub;

    private String name;
    private String taxId;
    private String address;
    private String contactName;
    @Column(nullable = false)
    private String contactEmail;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

