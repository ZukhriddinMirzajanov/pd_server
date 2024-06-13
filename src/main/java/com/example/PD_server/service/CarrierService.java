package com.example.PD_server.service;

import com.example.PD_server.dto.CarrierDto;
import com.example.PD_server.exception.ResourceNotFoundException;
import com.example.PD_server.model.Carrier;
import com.example.PD_server.repository.CarrierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrierService {

    @Autowired
    private CarrierRepository carrierRepository;

    public Carrier createCarrier(CarrierDto carrierDto) {
        Carrier carrier = new Carrier();
        carrier.setName(carrierDto.getName());
        carrier.setLicenseNumber(carrierDto.getLicenseNumber());
        carrier.setEnabled(carrierDto.isEnabled());
        return carrierRepository.save(carrier);
    }

    public List<Carrier> getAllCarriers() {
        return carrierRepository.findAll();
    }

    public Carrier getCarrierById(Long id) {
        return carrierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Carrier not found"));
    }

    public Carrier updateCarrier(Long id, CarrierDto carrierDto) {
        Carrier carrier = carrierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Carrier not found"));
        carrier.setName(carrierDto.getName());
        carrier.setLicenseNumber(carrierDto.getLicenseNumber());
        carrier.setEnabled(carrierDto.isEnabled());
        return carrierRepository.save(carrier);
    }

    public void deleteCarrier(Long id) {
        carrierRepository.deleteById(id);
    }

    public Carrier updateStatus(Long id, boolean enabled) {
        Carrier carrier = carrierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Carrier not found"));
        carrier.setEnabled(enabled);
        return carrierRepository.save(carrier);
    }

    public CarrierDto toDto(Carrier carrier) {
        CarrierDto carrierDto = new CarrierDto();
        carrierDto.setId(carrier.getId());
        carrierDto.setName(carrier.getName());
        carrierDto.setLicenseNumber(carrier.getLicenseNumber());
        carrierDto.setEnabled(carrier.isEnabled());
        return carrierDto;
    }
}
