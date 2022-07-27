package com.kkamgi.quiz.service;

import com.kkamgi.quiz.repository.SolveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SolveService {

    private final SolveRepository solveRepository;

    public boolean isSolved(Long memberId, Long quizId) {
        return solveRepository.existsByMemberIdAndQuizId(memberId, quizId);
    }
}
