package com.tropisure.adminserviceapi.repository;

import com.tropisure.adminserviceapi.entity.CarrierProfile;
import com.tropisure.adminserviceapi.entity.MGAProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CarrierProfileRepository extends JpaRepository<CarrierProfile, UUID> {
    Optional<CarrierProfile> findByContactEmail(String contactEmail);
}