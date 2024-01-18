package com.gastrosfera.shared.v1.user.dto;

import com.gastrosfera.shared.v1.base.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String username;
    private Long uid;
    private String role;
    private String message;  // pt info aditionale
    public AuthenticationResponse(String message) {
        this.message = message;
    }

    public AuthenticationResponse(String username, Long uid, String role) {
        this.username = username;
        this.uid = uid;
        this.role = role;
    }
}