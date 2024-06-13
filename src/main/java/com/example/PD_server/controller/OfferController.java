package com.example.PD_server.controller;

import com.example.PD_server.dto.OfferDto;
import com.example.PD_server.model.Offer;
import com.example.PD_server.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/offers")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @PostMapping
    public ResponseEntity<OfferDto> createOffer(@RequestBody OfferDto offerDto) {
        Offer offer = offerService.createOffer(offerDto);
        return ResponseEntity.ok(offerService.toDto(offer));
    }

    @GetMapping
    public ResponseEntity<List<OfferDto>> getAllOffers() {
        List<Offer> offers = offerService.getAllOffers();
        List<OfferDto> offerDtos = offers.stream().map(offerService::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(offerDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferDto> getOfferById(@PathVariable Long id) {
        Offer offer = offerService.getOfferById(id);
        return ResponseEntity.ok(offerService.toDto(offer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OfferDto> updateOffer(@PathVariable Long id, @RequestBody OfferDto offerDto) {
        Offer offer = offerService.updateOffer(id, offerDto);
        return ResponseEntity.ok(offerService.toDto(offer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);
        return ResponseEntity.noContent().build();
    }
}