package com.example.PD_server.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "app_carrier")
public class Carrier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String licenseNumber;
    private boolean enabled;
}
