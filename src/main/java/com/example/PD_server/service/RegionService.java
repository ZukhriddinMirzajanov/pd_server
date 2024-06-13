package com.example.PD_server.service;

import com.example.PD_server.dto.RegionDto;
import com.example.PD_server.exception.ResourceNotFoundException;
import com.example.PD_server.model.Region;
import com.example.PD_server.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public Region createRegion(RegionDto regionDto) {
        Region region = new Region();
        region.setName(regionDto.getName());
        return regionRepository.save(region);
    }

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public Region getRegionById(Long id) {
        return regionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Region not found"));
    }

    public Region updateRegion(Long id, RegionDto regionDto) {
        Region region = regionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Region not found"));
        region.setName(regionDto.getName());
        return regionRepository.save(region);
    }

    public void deleteRegion(Long id) {
        regionRepository.deleteById(id);
    }

    public RegionDto toDto(Region region) {
        RegionDto regionDto = new RegionDto();
        regionDto.setId(region.getId());
        regionDto.setName(region.getName());
        return regionDto;
    }
}
