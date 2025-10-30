package com.example.lingdingdong.service;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class SentenceService {
    private final List<String> sentences = List.of(
            "오늘 날씨가 정말 좋네요!",           // The weather is really nice today!
            "I’m learning Korean every day!",
            "한국 음식은 너무 맛있어요.",           // Korean food is so delicious.
            "Practice makes perfect!",
            "Don’t give up — keep studying!"
    );

    @Getter
    private String currentSentence;
    private final Random random = new Random();

    @PostConstruct
    public void init(){
        updateSentence();
    }

    @Scheduled(fixedRate = 600000)
    public void updateSentence(){
        currentSentence = sentences.get(random.nextInt(sentences.size()));
        System.out.println("New selected sentence: " + currentSentence);
    }

}
