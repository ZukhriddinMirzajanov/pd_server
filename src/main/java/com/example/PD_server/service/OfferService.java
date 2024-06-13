package com.example.PD_server.service;

import com.example.PD_server.dto.OfferDto;
import com.example.PD_server.exception.ResourceNotFoundException;
import com.example.PD_server.model.Carrier;
import com.example.PD_server.model.Offer;
import com.example.PD_server.repository.CarrierRepository;
import com.example.PD_server.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private CarrierRepository carrierRepository;

    public Offer createOffer(OfferDto offerDto) {
        Offer offer = new Offer();
        offer.setDescription(offerDto.getDescription());
        offer.setPrice(offerDto.getPrice());
        Carrier carrier = carrierRepository.findById(offerDto.getCarrierId()).orElseThrow(() -> new ResourceNotFoundException("Carrier not found"));
        offer.setCarrier(carrier);
        return offerRepository.save(offer);
    }

    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    public Offer getOfferById(Long id) {
        return offerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Offer not found"));
    }

    public Offer updateOffer(Long id, OfferDto offerDto) {
        Offer offer = offerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Offer not found"));
        offer.setDescription(offerDto.getDescription());
        offer.setPrice(offerDto.getPrice());
        Carrier carrier = carrierRepository.findById(offerDto.getCarrierId()).orElseThrow(() -> new ResourceNotFoundException("Carrier not found"));
        offer.setCarrier(carrier);
        return offerRepository.save(offer);
    }

    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }

    public OfferDto toDto(Offer offer) {
        OfferDto offerDto = new OfferDto();
        offerDto.setId(offer.getId());
        offerDto.setDescription(offer.getDescription());
        offerDto.setPrice(offer.getPrice());
        offerDto.setCarrierId(offer.getCarrier().getId());
        return offerDto;
    }
}

