package com.example.PD_server.dto;

import lombok.Data;

@Data
public class AdminDto {
    private Long id;
    private String username;
    private String email;
    private boolean enabled;
}
