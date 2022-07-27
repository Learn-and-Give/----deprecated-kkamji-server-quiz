package com.kkamgi.quiz.service;

import com.kkamgi.quiz.domain.Quiz;
import com.kkamgi.quiz.domain.QuizKeyword;
import com.kkamgi.quiz.domain.Quizbook;
import com.kkamgi.quiz.domain.data.QuizType;
import com.kkamgi.quiz.dto.response.GetQuizbooksResponse;
import com.kkamgi.quiz.repository.PurchaseRepository;
import com.kkamgi.quiz.repository.QuizbookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class QuizbookService {

    private final QuizbookRepository quizbookRepository;
    private final PurchaseRepository purchaseRepository;

    public List<GetQuizbooksResponse> getQuizbooks(Long memberId) {
        List<GetQuizbooksResponse> quizbooks = new ArrayList<>();
        quizbookRepository.findAll().forEach(quizbook -> {
            // 보유 여부 확인
            boolean isPurchased = purchaseRepository.existsByMemberIdAndQuizbookId(memberId, quizbook.getId());

            // 키워드 추출 및 중복 제거
            Set<String> keywords = new HashSet<String>();
            for (Quiz quiz : quizbook.getQuizs()) {
                for (QuizKeyword quizKeyword : quiz.getQuizKeywords()) {
                    keywords.add(quizKeyword.getKeyword().getName());
                }
            }

            // 문제집 리스트 응답 개체 생성
            GetQuizbooksResponse getQuizbooksResponse = GetQuizbooksResponse.builder()
                    .quizBookID(quizbook.getId())
                    .quizBookPrice(quizbook.getPrice())
                    .quizCnt(quizbook.getQuizs().size())
                    .choiceQuizCnt((int) quizbook.getQuizs().stream().filter(quiz -> quiz.getType() == QuizType.SINGLE_CHOICE).count())
                    .shortQuizCnt((int) quizbook.getQuizs().stream().filter(quiz -> quiz.getType() == QuizType.SHORT_ANSWER).count())
                    .longQuizCnt((int) quizbook.getQuizs().stream().filter(quiz -> quiz.getType() == QuizType.LONG_ANSWER).count())
                    .keywords(new ArrayList<String>(keywords))
                    .isOwned(isPurchased)
                    .build();
                quizbooks.add(getQuizbooksResponse);
            });


//        quizbookRepository.findAll().forEach(quizbook -> {
//            System.out.println("test2:" + quizbook.getQuizs().stream().map(quiz -> quiz.getId()));
//        });

        return quizbooks;
    }

    public Optional<Quizbook> getQuizbook(Long quizbookId) {
        return quizbookRepository.findById(quizbookId);
    }
}
