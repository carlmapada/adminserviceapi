package com.tropisure.adminserviceapi.controller;

import com.tropisure.adminserviceapi.entity.ReferenceCarrier;
import com.tropisure.adminserviceapi.entity.ReferenceMGA;
import com.tropisure.adminserviceapi.repository.ReferenceCarrierRepository;
import com.tropisure.adminserviceapi.repository.ReferenceMGARepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/reference")
@Tag(name = "List of MGA and Carriers", description = "Pull the list of MGA and Carries")
@RequiredArgsConstructor

public class ReferenceController {

    private final ReferenceMGARepository mgaRepo;
    private final ReferenceCarrierRepository carrierRepo;

    @GetMapping("/mga")
    @Operation(summary = "Pull the list of MGA")
    @SecurityRequirement(name = "bearerAuth")
    public List<ReferenceMGA> getMGAs() {
        return mgaRepo.findAll();
    }

    @GetMapping("/carrier")
    @Operation(summary = "Pull the list of Carriers by Country (US, CANADA)")
    public List<ReferenceCarrier> getCarriers(@RequestParam String country) {
        return carrierRepo.findByCountry(country);
    }
}


