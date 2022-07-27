package com.kkamgi.quiz.repository;

import com.kkamgi.quiz.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    boolean existsByMemberIdAndQuizbookId(Long memberId, Long quizbookId);
}
