package com.tropisure.adminserviceapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BulkUploadRequest {

    @Schema(description = "CSV or Excel file to upload", type = "string", format = "binary")
    private MultipartFile file;

    @Schema(description = "Type of upload", example = "MGA")
    private String type; // MGA or CARRIER
}
