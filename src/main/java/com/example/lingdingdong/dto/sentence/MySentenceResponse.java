package com.example.lingdingdong.dto.sentence;

import com.example.lingdingdong.entity.sentence.MySentence;

public record MySentenceResponse(
        String lang,
        String sentence,
        Long userId,
        String username
) {
    public static MySentenceResponse from(MySentence mySentence){
        return new MySentenceResponse(
                mySentence.getLang().getCode(),
                mySentence.getSentence(),
                mySentence.getUser().getId(),
                mySentence.getUser().getUsername()
        );
    }
}
