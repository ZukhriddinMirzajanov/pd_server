package com.example.PD_server.dto;

import lombok.Data;

@Data
public class TransactionDto {
    private Long id;
    private double amount;
    private Long requestId;
    private Long offerId;
    private String transactionCode;
    private String carrierUsername;
    private String requestCode;
    private String offerCode;
    private int score;

    public TransactionDto() {
        // Default constructor
    }

    public TransactionDto(String transactionCode, int score) {
        this.transactionCode = transactionCode;
        this.score = score;
    }
}
