package com.example.lingdingdong.service.sentence;

import com.example.lingdingdong.enums.LanguageCode;

public interface SentenceService {
    LanguageCode getCurrentLanguage();

    String getCurrentSentence();

    void setCurrentLanguage(String lang);

    void updateSentence();
}
