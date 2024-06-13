package com.example.PD_server.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "app_transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;
    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;
    @Column(name = "transaction_code", nullable = false, unique = true)
    private String transactionCode;

    @Column(name = "score", nullable = false)
    private int score;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}