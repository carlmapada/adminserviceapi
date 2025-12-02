package com.tropisure.adminserviceapi.repository;

import com.tropisure.adminserviceapi.entity.CarrierProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarrierProfileRepository extends JpaRepository<CarrierProfile, UUID> {}