package com.tropisure.adminserviceapi.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BulkUploadRequest {
    private MultipartFile file;
    private String type; // MGA or CARRIER
}
