package com.example.lingdingdong.dto.auth;

public record LoginResponse(
        String accessToken,
        String refreshToken
) {
}
