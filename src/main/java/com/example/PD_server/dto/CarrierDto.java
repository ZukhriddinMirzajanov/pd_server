package com.example.PD_server.dto;

import lombok.Data;

@Data
public class CarrierDto {
    private Long id;
    private String name;
    private String licenseNumber;
    private boolean enabled;
}
