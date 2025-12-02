package com.tropisure.adminserviceapi.controller;

import com.tropisure.adminserviceapi.dto.CreateCarrierRequest;
import com.tropisure.adminserviceapi.dto.CreateMGARequest;
import com.tropisure.adminserviceapi.dto.ResponseDto;
import com.tropisure.adminserviceapi.entity.CarrierProfile;
import com.tropisure.adminserviceapi.entity.MGAProfile;
import com.tropisure.adminserviceapi.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/mga")
    public ResponseEntity<ResponseDto> createMGA(@RequestBody CreateMGARequest request) {
        MGAProfile mga = adminService.createMGA(request, "SUPERADMIN");
        return ResponseEntity.ok(ResponseDto.builder().message("MGA created").data(mga).build());
    }

    @PostMapping("/carrier")
    public ResponseEntity<ResponseDto> createCarrier(@RequestBody CreateCarrierRequest request) {
        CarrierProfile carrier = adminService.createCarrier(request, "SUPERADMIN");
        return ResponseEntity.ok(ResponseDto.builder().message("Carrier created").data(carrier).build());
    }
}


