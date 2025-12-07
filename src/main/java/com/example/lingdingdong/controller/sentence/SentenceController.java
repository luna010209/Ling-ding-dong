package com.example.lingdingdong.controller.sentence;

import com.example.lingdingdong.dto.sentence.SentenceResponse;
import com.example.lingdingdong.service.sentence.SentenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sentence")
public class SentenceController {
    private final SentenceService sentenceService;
    @PostMapping
    public String changeLanguage(@RequestParam String language){
        sentenceService.setCurrentLanguage(language);
        return sentenceService.getCurrentSentence();
    }

    @GetMapping
    public SentenceResponse getCurrentSentence(){
        return new SentenceResponse(
                sentenceService.getCurrentLanguage().name(),
                sentenceService.getCurrentSentence()
        );
    }
}
