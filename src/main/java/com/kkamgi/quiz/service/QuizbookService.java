package com.kkamgi.quiz.service;

import com.kkamgi.quiz.domain.Quiz;
import com.kkamgi.quiz.domain.QuizKeyword;
import com.kkamgi.quiz.domain.Quizbook;
import com.kkamgi.quiz.domain.data.QuizType;
import com.kkamgi.quiz.dto.response.FindQuizbooksResponseDto;
import com.kkamgi.quiz.repository.PurchaseRepository;
import com.kkamgi.quiz.repository.QuizbookRepository;
import com.kkamgi.quiz.repository.SolveRepository;
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

    public List<FindQuizbooksResponseDto> findQuizbooks(Long memberId) {
        List<FindQuizbooksResponseDto> quizbooks = new ArrayList<>();
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
            FindQuizbooksResponseDto findQuizbooksResponseDto = FindQuizbooksResponseDto.builder()
                    .quizBookID(quizbook.getId())               // 문제집 아이디
                    .quizBookPrice(quizbook.getPrice())         // 문제집 가격
                    .quizCnt(quizbook.getQuizs().size())        // 문제 개수
                    .choiceQuizCnt((int) quizbook.getQuizs().stream().filter(quiz -> quiz.getType() == QuizType.SINGLE_CHOICE).count())     // 객관식 문제 개수
                    .shortQuizCnt((int) quizbook.getQuizs().stream().filter(quiz -> quiz.getType() == QuizType.SHORT_ANSWER).count())       // 단답형 문제 개수
                    .longQuizCnt((int) quizbook.getQuizs().stream().filter(quiz -> quiz.getType() == QuizType.LONG_ANSWER).count())         // 서술형 문제 개수
                    .keywords(new ArrayList<String>(keywords))      // 키워드 리스트
                    .isOwned(isPurchased)                           // 회원이 이 문제집을 보유하고 있는가
                    .build();
                quizbooks.add(findQuizbooksResponseDto);
            });

        return quizbooks;
    }

    public Optional<Quizbook> findOne(Long quizbookId) {
        return quizbookRepository.findById(quizbookId);
    }

    public List<Quiz> findQuizs(Quizbook quizbook) {
        return quizbook.getQuizs();
    }

    public List<String> findTypesByQuizDistincs(List<Quiz> quizs) {
        Set<String> types = new HashSet<String>();

        quizs.forEach(quiz -> {
            types.add(quiz.getType().toString());
        });

        return (List<String>) types;
    }
}
