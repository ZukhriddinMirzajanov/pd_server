package com.example.PD_server.controller;

import com.example.PD_server.dto.CarrierDto;
import com.example.PD_server.model.Carrier;
import com.example.PD_server.service.CarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/carriers")
public class CarrierController {

    @Autowired
    private CarrierService carrierService;

    @PostMapping
    public ResponseEntity<CarrierDto> createCarrier(@RequestBody CarrierDto carrierDto) {
        Carrier carrier = carrierService.createCarrier(carrierDto);
        return ResponseEntity.ok(carrierService.toDto(carrier));
    }

    @GetMapping
    public ResponseEntity<List<CarrierDto>> getAllCarriers() {
        List<Carrier> carriers = carrierService.getAllCarriers();
        List<CarrierDto> carrierDtos = carriers.stream().map(carrierService::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(carrierDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarrierDto> getCarrierById(@PathVariable Long id) {
        Carrier carrier = carrierService.getCarrierById(id);
        return ResponseEntity.ok(carrierService.toDto(carrier));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarrierDto> updateCarrier(@PathVariable Long id, @RequestBody CarrierDto carrierDto) {
        Carrier carrier = carrierService.updateCarrier(id, carrierDto);
        return ResponseEntity.ok(carrierService.toDto(carrier));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarrier(@PathVariable Long id) {
        carrierService.deleteCarrier(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<CarrierDto> updateStatus(@PathVariable Long id, @RequestParam boolean enabled) {
        Carrier carrier = carrierService.updateStatus(id, enabled);
        return ResponseEntity.ok(carrierService.toDto(carrier));
    }
}
