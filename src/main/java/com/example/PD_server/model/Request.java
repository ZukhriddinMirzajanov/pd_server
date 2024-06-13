package com.example.PD_server.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "app_request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @Column(name = "request_code", nullable = false, unique = true)
    private String requestCode;

    @Column(name = "place_name", nullable = false)
    private String placeName;

    @Column(name = "product_code", nullable = false)
    private String productCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
