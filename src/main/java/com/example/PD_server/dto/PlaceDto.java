package com.example.PD_server.dto;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PlaceDto {
    private Long id;
    private String name;
    private Long regionId;
    private String regionName;
}
