package com.example.PD_server.request;

import com.example.PD_server.security.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}