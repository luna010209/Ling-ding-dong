package com.example.lingdingdong.dto.auth;

import com.example.lingdingdong.entity.auth.Auth;
import com.example.lingdingdong.enums.Role;

import java.util.List;

public record UserInfo(
        String username,
        String email,
        List<String> roles
) {
    public static UserInfo from(Auth user){
        return new UserInfo(
                user.getUsername(),
                user.getEmail(),
                user.getRoles().stream().map(Role::getAuthority).toList()
        );
    }
}
