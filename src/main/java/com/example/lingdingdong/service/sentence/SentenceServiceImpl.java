package com.example.lingdingdong.service.sentence;

import com.example.lingdingdong.enums.LanguageCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class SentenceServiceImpl implements SentenceService {
    private final Map<LanguageCode, List<String>> sentencesByLang = Map.of(
            LanguageCode.EN, List.of(
                    "I'm learning English everyday",
                    "Don't give up!",
                    "Could you pass me a glass of water?",
                    "Would you like a piece of cake?",
                    "What time do you usually get up?",
                    "You should practice English everyday"
            ),
            LanguageCode.KO, List.of(
                    "매일 한국어를 배우고 있어요",
                    "포기하지 마세요!",
                    "물을 건네주실래요?",
                    "케이크를 한 조각 드실래요?",
                    "몇 시에 일어나세요?",
                    "매일 한국어를 연습해야 해요"
            ),
            LanguageCode.VI, List.of(
                    "Tôi học tiếng Việt mỗi ngày",
                    "Đừng bỏ cuộc!",
                    "Bạn có thể đưa cho tôi cốc nước không?",
                    "Bạn có muốn một miếng bánh không?",
                    "Bạn thường thức dậy vào mấy giờ?",
                    "Bạn nên luyện tập tiếng Việt mỗi ngày"
            ),
            LanguageCode.JA, List.of(
                    "私は毎日日本語を勉強しています",
                    "諦めないでください!",
                    "水を渡してくれますか？",
                    "ケーキを一切れいかがですか？",
                    "何時に起きますか？",
                    "毎日日本語を練習すべきです"
            )
    );

    private final Random random = new Random();

    @Getter
    private volatile LanguageCode currentLanguage = LanguageCode.EN;

    @Getter
    @Setter
    private volatile String currentSentence;

    public void updateSentence() {
        List<String> list = sentencesByLang.getOrDefault(currentLanguage, List.of());
        String oldSentence = currentSentence;
        if (!list.isEmpty()) {
            currentSentence = list.get(random.nextInt(list.size()));
            while (currentSentence.equals(oldSentence)){
                currentSentence = list.get(random.nextInt(list.size()));
            }
//            System.out.println("New sentence (" + currentLanguage + "): " + currentSentence);
        }
    }

    public void setCurrentLanguage(String lang) {
        LanguageCode code = LanguageCode.valueOf(lang.toUpperCase());
        this.currentLanguage = code;
        updateSentence();
    }
}
