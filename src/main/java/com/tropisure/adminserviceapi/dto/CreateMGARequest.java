package com.tropisure.adminserviceapi.dto;

import lombok.Data;

@Data
public class CreateMGARequest {
    private String name;
    private String taxId;
    private String address;
    private String contactName;
    private String contactEmail;
}

