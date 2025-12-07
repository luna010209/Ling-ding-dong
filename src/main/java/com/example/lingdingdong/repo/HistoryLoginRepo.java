package com.example.lingdingdong.repo;

import com.example.lingdingdong.entity.auth.HistoryLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryLoginRepo extends JpaRepository<HistoryLogin, Long> {
}
