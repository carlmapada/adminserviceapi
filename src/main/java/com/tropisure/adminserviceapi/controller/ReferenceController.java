package com.tropisure.adminserviceapi.controller;

import com.tropisure.adminserviceapi.entity.ReferenceCarrier;
import com.tropisure.adminserviceapi.entity.ReferenceMGA;
import com.tropisure.adminserviceapi.repository.ReferenceCarrierRepository;
import com.tropisure.adminserviceapi.repository.ReferenceMGARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/reference")
@RequiredArgsConstructor
public class ReferenceController {

    private final ReferenceMGARepository mgaRepo;
    private final ReferenceCarrierRepository carrierRepo;

    @GetMapping("/mga")
    public List<ReferenceMGA> getMGAs() {
        return mgaRepo.findAll();
    }

    @GetMapping("/carrier")
    public List<ReferenceCarrier> getCarriers(@RequestParam String country) {
        return carrierRepo.findByCountry(country);
    }
}


