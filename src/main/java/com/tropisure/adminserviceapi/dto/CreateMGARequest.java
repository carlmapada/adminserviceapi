package com.tropisure.adminserviceapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateMGARequest {

    @Schema(description = "Name of the MGA", example = "Sunrise MGA")
    private String name;

    @Schema(description = "Tax Identification Number", example = "123-45-6789")
    private String taxId;

    @Schema(description = "MGA business address", example = "123 Main St, Chicago, IL 60601")
    private String address;

    @Schema(description = "Primary contact person name", example = "John Doe")
    private String contactName;

    @Schema(description = "Primary contact email", example = "johndoe@sunrisemga.com")
    private String contactEmail;
}

