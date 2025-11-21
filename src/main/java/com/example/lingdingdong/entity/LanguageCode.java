package com.example.lingdingdong.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public enum LanguageCode {
    KO("ko"),
    EN("en"),
    VI("vi"),
    JA("ja");

    @Getter
    private final String code;
}
