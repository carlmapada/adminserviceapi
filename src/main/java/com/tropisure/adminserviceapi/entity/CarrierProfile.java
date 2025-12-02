package com.tropisure.adminserviceapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "carrier_profile", schema = "registration_db")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarrierProfile {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String naicCode;
    private String contactEmail;
    private String contactName;
    @Column(length = 2)
    private String country;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

