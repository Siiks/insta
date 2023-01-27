package com.example.inst.Payload.Response;

import lombok.Data;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String tlf;
    private String username;
    private String email;

    public JwtResponse(String accessToken, Long id, String username, String
            email, String tlf) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.tlf = tlf;
    }
}
