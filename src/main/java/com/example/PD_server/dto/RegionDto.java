package com.example.PD_server.dto;

import com.example.PD_server.model.Place;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RegionDto {
    private Long id;
    private String name;
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PlaceDto> places = new HashSet<>();

    public RegionDto() {
    }

    public RegionDto(String name, Set<PlaceDto> places) {
        this.name = name;
        this.places = places;
    }
}
