package com.example.PD_server.controller;

import com.example.PD_server.dto.RegionDto;
import com.example.PD_server.model.Region;
import com.example.PD_server.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/regions")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @PostMapping
    public ResponseEntity<RegionDto> createRegion(@RequestBody RegionDto regionDto) {
        Region region = regionService.createRegion(regionDto);
        return ResponseEntity.ok(regionService.toDto(region));
    }

    @GetMapping
    public ResponseEntity<List<RegionDto>> getAllRegions() {
        List<Region> regions = regionService.getAllRegions();
        List<RegionDto> regionDtos = regions.stream().map(regionService::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(regionDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionDto> getRegionById(@PathVariable Long id) {
        Region region = regionService.getRegionById(id);
        return ResponseEntity.ok(regionService.toDto(region));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegionDto> updateRegion(@PathVariable Long id, @RequestBody RegionDto regionDto) {
        Region region = regionService.updateRegion(id, regionDto);
        return ResponseEntity.ok(regionService.toDto(region));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegion(@PathVariable Long id) {
        regionService.deleteRegion(id);
        return ResponseEntity.noContent().build();
    }
}
