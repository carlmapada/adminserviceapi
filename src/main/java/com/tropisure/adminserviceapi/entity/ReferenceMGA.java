package com.tropisure.adminserviceapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "reference_mga", schema = "admin_db")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReferenceMGA {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String country;
    private LocalDateTime createdAt;
}

