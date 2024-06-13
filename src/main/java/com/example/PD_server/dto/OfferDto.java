package com.example.PD_server.dto;

import com.example.PD_server.model.Carrier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class OfferDto {
    private Long id;
    private String offerCode;
    private String placeName;
    private String productCode;
    private Long userId;
    private String username;
    private String description;
    private double price;
    private Long carrierId;
    private Carrier carrier;

    public OfferDto() {
        // Default constructor
    }

    public OfferDto(String offerCode, String placeName, String productCode) {
        this.offerCode = offerCode;
        this.placeName = placeName;
        this.productCode = productCode;
    }
}
