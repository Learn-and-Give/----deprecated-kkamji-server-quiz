package com.kkamgi.quiz.service;

import com.kkamgi.quiz.domain.Quizbook;
import com.kkamgi.quiz.repository.QuizbookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class QuizbookService {
    private final QuizbookRepository quizbookRepository;

    public List<Quizbook> getQuizbooks() {
        return quizbookRepository.findAll();
    }

    public Optional<Quizbook> getQuizbook(Long quizbookId) {
        return quizbookRepository.findById(quizbookId);
    }
}
