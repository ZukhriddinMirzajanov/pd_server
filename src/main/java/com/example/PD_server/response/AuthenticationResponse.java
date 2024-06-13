package com.example.PD_server.response;

import com.example.PD_server.security.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private String token;
}
