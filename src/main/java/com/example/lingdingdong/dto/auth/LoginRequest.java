package com.example.lingdingdong.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Please input your email or phone")
        String email,

        @NotBlank(message = "Please input your password")
        String password
) {
}
