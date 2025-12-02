package com.tropisure.adminserviceapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarrierRequest {
    private String name;
    private String naicCode;
    private String country; // US or CA
    private String contactEmail;
}

