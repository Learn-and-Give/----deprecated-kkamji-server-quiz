package com.kkamgi.quiz.controller;

import com.kkamgi.quiz.domain.Quizbook;
import com.kkamgi.quiz.service.QuizbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/v1")
@RestController
public class QuizbookController {
    private final QuizbookService quizbookService;

    @GetMapping("/quizbooks")
    public ResponseMapper<List<Quizbook>> getQuizbooks(@RequestHeader("code") String code) {
        return ResponseMapper.register(200, "OK", quizbookService.getQuizbooks());
    }

    @GetMapping("/quizbooks/{quizbookId}")
    public ResponseMapper<Quizbook> getQuizbook(@PathVariable("quizbookId") Long quizbookId) {
        Optional<Quizbook> quizbook = quizbookService.getQuizbook(quizbookId);
        if (quizbook.isPresent()) {
            return ResponseMapper.register(200, "OK", quizbook.get());
        } else {
            return ResponseMapper.register(404, "Not Found", null);
        }
    }
}
