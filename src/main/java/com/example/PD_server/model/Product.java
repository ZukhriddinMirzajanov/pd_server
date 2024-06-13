package com.example.PD_server.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
@Table(name = "app_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product code is mandatory")
    @Column(unique = true)
    private String productCode;

    @NotBlank(message = "Product name is mandatory")
    @Column(unique = true)
    private String productName;

    @Positive(message = "Product price must be a positive number")
    private double productPrice;

    private String productImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
