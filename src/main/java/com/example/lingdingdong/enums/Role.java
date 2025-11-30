package com.example.lingdingdong.enums;

import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

@ToString
public enum Role implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER,
    ;


    @Override
    public String getAuthority() {
        return name();
    }
}
