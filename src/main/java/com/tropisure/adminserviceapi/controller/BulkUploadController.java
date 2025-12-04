package com.tropisure.adminserviceapi.controller;

import com.tropisure.adminserviceapi.dto.BulkUploadRequest;
import com.tropisure.adminserviceapi.dto.ResponseDto;
import com.tropisure.adminserviceapi.service.BulkUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Tag(name = "MGA and Carrier Management", description = "Bulk Creation of MGA and Carrier profiles")
public class BulkUploadController {

    private final BulkUploadService bulkService;

    @PostMapping("/bulk")
    @Operation(summary = "Bulk creation of MGA and Carrier Profile")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ResponseDto> bulkUpload(@ModelAttribute MultipartFile file, @RequestParam String type) throws Exception {
        BulkUploadRequest request = new BulkUploadRequest();
        request.setFile(file);
        request.setType(type);
        bulkService.processBulkUpload(request, "SUPERADMIN");
        return ResponseEntity.ok(ResponseDto.builder().message("Bulk upload completed").build());
    }
}

