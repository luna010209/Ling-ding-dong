package com.example.lingdingdong.controller;

import com.example.lingdingdong.service.SentenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/language")
public class LanguageController {
    private final SentenceService sentenceService;
    @PostMapping
    public String changeLanguage(@RequestParam String language){
        sentenceService.setCurrentLanguage(language);
        return sentenceService.getCurrentSentence();
    }

    @GetMapping
    public Map<String, String> getCurrentSentence(){
        return Map.of(
            "language", sentenceService.getCurrentLanguage().toString(),
            "sentence", sentenceService.getCurrentSentence()
        );
    }
}
