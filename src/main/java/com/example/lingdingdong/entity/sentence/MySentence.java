package com.example.lingdingdong.entity.sentence;

import com.example.lingdingdong.entity.auth.Auth;
import com.example.lingdingdong.enums.LanguageCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@AllArgsConstructor
@Builder
@Table(name = "ldd_sentence")
public class MySentence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sentence")
    private String sentence;

    @Column(name = "lang")
    private LanguageCode lang;

    @ManyToOne(fetch = FetchType.LAZY)
    private Auth user;
}
