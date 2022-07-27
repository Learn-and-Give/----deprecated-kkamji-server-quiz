package com.kkamgi.quiz.repository;

import com.kkamgi.quiz.domain.Solve;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolveRepository extends JpaRepository<Solve, Long> {
    boolean existsByMemberIdAndQuizId(Long memberId, Long quizId);
}
