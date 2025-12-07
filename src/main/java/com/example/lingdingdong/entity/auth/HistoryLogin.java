package com.example.lingdingdong.entity.auth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@Builder
@Table(name = "ldd_history_login")
public class HistoryLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Auth user;

    private String ipAddress;

    private String userAgent;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }

    private HistoryLogin(Auth user, String ipAddress, String userAgent){
        this.user = user;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
    }
}
