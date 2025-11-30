package com.example.lingdingdong.service.auth;

import com.example.lingdingdong.dto.auth.SignUpRequest;

public interface AuthService {
    Long newUser(SignUpRequest request);
}
