package com.example.PD_server.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "app_offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Offer code is mandatory")
    @Size(min = 3, message = "Offer code must be at least 3 characters long")
    @Column(unique = true)
    private String offerCode;

    @NotBlank(message = "Place name is mandatory")
    private String placeName;

    @NotBlank(message = "Product code is mandatory")
    private String productCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrier_id", nullable = false)
    private Carrier carrier;

    private String description;
    private double price;
}
