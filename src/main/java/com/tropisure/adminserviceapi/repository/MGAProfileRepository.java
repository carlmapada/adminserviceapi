package com.tropisure.adminserviceapi.repository;

import com.tropisure.adminserviceapi.entity.MGAProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MGAProfileRepository extends JpaRepository<MGAProfile, UUID> {}
