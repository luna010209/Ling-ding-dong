package com.example.lingdingdong.service.auth;

import com.example.lingdingdong.dto.auth.LoginRequest;
import com.example.lingdingdong.dto.auth.LoginResponse;
import com.example.lingdingdong.dto.auth.SignUpRequest;
import com.example.lingdingdong.dto.auth.UserInfo;
import com.example.lingdingdong.entity.auth.Auth;
import com.example.lingdingdong.entity.auth.HistoryLogin;
import com.example.lingdingdong.exception.CustomException;
import com.example.lingdingdong.jwt.CustomUserDetails;
import com.example.lingdingdong.jwt.JwtProperties;
import com.example.lingdingdong.jwt.TokenProvider;
import com.example.lingdingdong.repo.AuthRepo;
import com.example.lingdingdong.repo.HistoryLoginRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final AuthRepo authRepo;
    private final PasswordEncoder encoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final HistoryLoginRepo historyLoginRepo;
    private final JwtProperties jwtProperties;

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
                .password(encoder.encode(request.password()))
                .roles(Set.of(request.role()))
                .build();

        authRepo.save(user);
        return user.getId();
    }

    @Override
    @Transactional
    public LoginResponse authenticate(LoginRequest request, String ipAddress, String userAgent) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.email(), request.password());

            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            Auth user = userDetails.getUser();

            user.setLastLogin(LocalDateTime.now());

            HistoryLogin historyLogin = HistoryLogin.builder()
                    .user(user)
                    .ipAddress(ipAddress)
                    .userAgent(userAgent)
                    .build();

            historyLoginRepo.save(historyLogin);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String accessToken = TokenProvider.releaseToken(authentication, jwtProperties);
            String refreshToken = TokenProvider.refreshToken(authentication, jwtProperties);

            return new LoginResponse(accessToken, refreshToken);
        } catch (AuthenticationException e){
            throw new CustomException(HttpStatus.UNAUTHORIZED, "Please check your email/phone number or password!");
        }
    }

    @Override
    public UserInfo userLogin() {
        String emailOrPhone = SecurityContextHolder.getContext().getAuthentication().getName();
        Auth user = null;
        if (authRepo.existsByEmail(emailOrPhone))
            user = authRepo.findByEmail(emailOrPhone).orElseThrow();
        if (user == null) throw new CustomException(HttpStatus.BAD_REQUEST, "No user login");
        return UserInfo.from(user);
    }

}
