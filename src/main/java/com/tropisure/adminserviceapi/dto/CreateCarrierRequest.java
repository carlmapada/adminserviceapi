package com.tropisure.adminserviceapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateCarrierRequest {

    @Schema(description = "Carrier name", example = "ABC Insurance")
    private String name;

    @Schema(description = "NAIC code of the carrier", example = "12345")
    private String naicCode;

    @Schema(description = "Contact email of the carrier", example = "contact@abc.com")
    private String contactEmail;

    @Schema(description = "Country of the carrier", example = "US", allowableValues = {"US", "CA"})
    private String country; // US or CA
}

