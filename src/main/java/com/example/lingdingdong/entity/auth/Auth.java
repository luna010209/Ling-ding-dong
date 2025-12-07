package com.example.lingdingdong.entity.auth;

import com.example.lingdingdong.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@Builder
@Table(name = "ldd_auth")
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 20)
    private String username;

    @Column(name = "password", length = 25)
    private String password;

    @Column(name = "email")
    private String email;

    @Setter
    private LocalDateTime lastLogin;

    @Column(name = "role")
    @Builder.Default
    private Set<Role> roles = new HashSet<>();
}
