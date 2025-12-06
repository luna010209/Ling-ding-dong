package com.example.lingdingdong.repo;

import com.example.lingdingdong.entity.auth.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepo extends JpaRepository<Auth, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<Auth> findByEmail (String email);
}
