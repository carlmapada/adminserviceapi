package com.tropisure.adminserviceapi.dto;

import lombok.Data;

@Data
public class CreateCarrierRequest {
    private String name;
    private String naicCode;
    private String contactEmail;
    private String country; // US or CA
}

