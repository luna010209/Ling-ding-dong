package com.example.lingdingdong.controller;

import com.example.lingdingdong.service.SentenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class SentenceController {
    private final SimpMessagingTemplate template;
    private final SentenceService sentenceService;

    @Scheduled(fixedRate = 600000)
    public void sendSentence(){
        String sentence = sentenceService.getCurrentSentence();
        template.convertAndSend("/topic/sentence", sentence);
        System.out.println("Sent sentence: "+ sentence);
    }
}
