package com.example.lingdingdong.controller;

import com.example.lingdingdong.service.SentenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/language")
public class LanguageController {
    private final SentenceService sentenceService;
    @PostMapping
    public void changeLanguage(@RequestParam String language){
        sentenceService.setCurrentLanguage(language);
    }
}
