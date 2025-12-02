package com.tropisure.adminserviceapi.repository;

import com.tropisure.adminserviceapi.entity.ReferenceCarrier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReferenceCarrierRepository extends JpaRepository<ReferenceCarrier, Long> {
    List<ReferenceCarrier> findByCountry(String country);
}
