package com.example.lingdingdong.service.auth;

import com.example.lingdingdong.dto.auth.LoginRequest;
import com.example.lingdingdong.dto.auth.LoginResponse;
import com.example.lingdingdong.dto.auth.SignUpRequest;
import com.example.lingdingdong.dto.auth.UserInfo;

public interface AuthService {
    Long newUser(SignUpRequest request);

    LoginResponse authenticate(LoginRequest request, String ipAddress, String userAgent);

    UserInfo userLogin();
}
