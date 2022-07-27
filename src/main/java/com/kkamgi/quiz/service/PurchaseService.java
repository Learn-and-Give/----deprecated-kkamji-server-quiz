package com.kkamgi.quiz.service;

import com.kkamgi.quiz.repository.PurchaseRepository;
import com.kkamgi.quiz.repository.SolveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    public boolean isPurchased(Long memberId, Long quizbookId) {
        return purchaseRepository.existsByMemberIdAndQuizbookId(memberId, quizbookId);
    }
}
