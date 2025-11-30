package com.example.lingdingdong.service.auth;

import com.example.lingdingdong.dto.auth.SignUpRequest;
import com.example.lingdingdong.entity.auth.Auth;
import com.example.lingdingdong.exception.CustomException;
import com.example.lingdingdong.repo.AuthRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final AuthRepo authRepo;

    @Override
    public Long newUser(SignUpRequest request) {
        if (authRepo.existsByUsername(request.username()))
            throw new CustomException(HttpStatus.CONFLICT, "Username already exists");
        else if (authRepo.existsByEmail(request.email()))
            throw new CustomException(HttpStatus.CONFLICT, "Email already exists");
        else if (!request.password().equals(request.cfPassword()))
            throw new CustomException(HttpStatus.CONFLICT, "Password and Confirm password do not match!");

        Auth user = Auth.builder()
                .username(request.username())
                .email(request.email())
                .build();
        return 0L;
    }
}
