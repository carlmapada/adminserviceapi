package com.tropisure.adminserviceapi.controller;

import com.tropisure.adminserviceapi.dto.BulkUploadRequest;
import com.tropisure.adminserviceapi.dto.ResponseDto;
import com.tropisure.adminserviceapi.service.BulkUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class BulkUploadController {

    private final BulkUploadService bulkService;

    @PostMapping("/bulk")
    public ResponseEntity<ResponseDto> bulkUpload(@RequestParam MultipartFile file, @RequestParam String type) throws Exception {
        BulkUploadRequest request = new BulkUploadRequest();
        request.setFile(file);
        request.setType(type);
        bulkService.processBulkUpload(request, "SUPERADMIN");
        return ResponseEntity.ok(ResponseDto.builder().message("Bulk upload completed").build());
    }
}

