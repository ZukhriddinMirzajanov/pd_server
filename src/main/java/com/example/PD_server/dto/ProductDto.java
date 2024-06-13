package com.example.PD_server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductDto {
    @NotBlank(message = "Product code is mandatory")
    private String productCode;

    @NotBlank(message = "Product name is mandatory")
    private String productName;

    @Positive(message = "Product price must be a positive number")
    private double productPrice;

    private String productImage;

    public ProductDto() {
    }

    public ProductDto(String productCode, String productName, double productPrice, String productImage) {
        this.productCode = productCode;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImage = productImage;
    }

}
