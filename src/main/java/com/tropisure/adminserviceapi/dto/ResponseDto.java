package com.tropisure.adminserviceapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class ResponseDto {

    @Schema(description = "Message describing the result", example = "MGA created successfully")
    private String message;

    @Schema(description = "Response data (can be any object)", example = "{ \"id\": 1, \"name\": \"Sunrise MGA\" }")
    private Object data;
}

