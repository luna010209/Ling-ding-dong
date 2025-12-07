package com.example.lingdingdong.controller.sentence;

import com.example.lingdingdong.service.sentence.MySentenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/my-sentence")
public class MySentenceController {
    private final MySentenceService mySentenceService;


}
