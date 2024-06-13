package com.example.PD_server.dto;

import lombok.Data;

@Data
public class RequestDto {
    private Long id;
    private String description;
    private Long regionId;
    private String requestCode;
    private String placeName;
    private String productCode;

    public RequestDto() {
        // Default constructor
    }

    public RequestDto(String requestCode, String placeName, String productCode) {
        this.requestCode = requestCode;
        this.placeName = placeName;
        this.productCode = productCode;
    }
}
