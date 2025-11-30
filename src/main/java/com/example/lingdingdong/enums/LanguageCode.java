package com.example.lingdingdong.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum LanguageCode {
    KO("ko"),
    EN("en"),
    VI("vi"),
    JA("ja");

    @Getter
    private final String code;
}
