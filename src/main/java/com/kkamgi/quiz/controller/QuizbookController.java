package com.kkamgi.quiz.controller;

import com.kkamgi.quiz.domain.Quiz;
import com.kkamgi.quiz.domain.Quizbook;
import com.kkamgi.quiz.dto.response.FindOneQuizResponseDto;
import com.kkamgi.quiz.dto.response.FindOneQuizbookResponseDto;
import com.kkamgi.quiz.dto.response.FindQuizbooksResponseDto;
import com.kkamgi.quiz.service.MemberService;
import com.kkamgi.quiz.service.PurchaseService;
import com.kkamgi.quiz.service.QuizbookService;
import com.kkamgi.quiz.service.SolveService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/v1")
@RestController
public class QuizbookController {
    private final QuizbookService quizbookService;
    private final MemberService memberService;
    private final SolveService solveService;
    private final PurchaseService purchaseService;

    @ApiOperation(value = "문제집 리스트 조회", notes = "문제집 아이디, 가격, 보유 여부, 문제 수, 유형별 수, 키워드 리스트를 조회한다.")
    @ApiImplicitParam(name = "code", value = "로그인한 회원 코드", required = true, dataType = "String", paramType = "header", example = "0000")
    @GetMapping("/quizbooks")
    public ResponseEntity<List<FindQuizbooksResponseDto>> getQuizbooks(@RequestHeader(value = "code") String code) {
        if (memberService.existsByPassword(code)) {     // 로그인 여부 확인
            Long memberId = memberService.findOne(code).get().getId();
            return new ResponseEntity<>(quizbookService.findQuizbooks(memberId), HttpStatus.OK);   // 정상 응답
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);      // 로그인 실패
    }

    @ApiOperation(value = "문제집 상세 조회", notes = "문제집 아이디, 타입, 풂 여부, 총 문제 수, 문제 내용, 문제집 보유 여부를 조회한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "code", value = "로그인한 회원 코드", required = true, dataType = "String", paramType = "header", example = "0000"),
        @ApiImplicitParam(name = "quizbookId", value = "문제집 아이디", required = true, dataType = "Long", paramType = "path", example = "1")
    })
    @GetMapping("/quizbooks/{quizbookId}")
    public ResponseMapper<FindOneQuizbookResponseDto> getQuizbook(@RequestHeader(value = "code") String code, @PathVariable(value = "quizbookId") Long quizbookId) {
        if (memberService.existsByPassword(code)) {     // 로그인 여부 확인

            // 존재하는 문제집인지 확인
            Optional<Quizbook> quizbookServiceOne = quizbookService.findOne(quizbookId);
            if (quizbookServiceOne.isEmpty()) return ResponseMapper.register(HttpStatus.NOT_FOUND);   // 데이터가 없으면 NOT_FOUND 응답

            Quizbook quizbook = quizbookServiceOne.get();               // 현재 보고있는 문제집 식별
            Long memberId = memberService.findOne(code).get().getId();  // 현재 로그인한 유저 식별
            List<Quiz> quizs = quizbookService.findQuizs(quizbook);     // 문제집 안에 문제들

            // 문제 정보 저장
            List<FindOneQuizResponseDto> findOneQuizResponseDtos = new ArrayList<>(quizs.size());
            for (Quiz quiz : quizs) {
                findOneQuizResponseDtos.add(FindOneQuizResponseDto.builder()
                        .quizID(quiz.getId())                                       // 퀴즈 아이디
                        .quizType(quiz.getType().toString())                        // 퀴즈 유형
                        .isSolved(solveService.isSolved(memberId, quiz.getId()))    // 풀었는지 여부
                        .quizContent(quiz.getContent())                             // 퀴즈 문제 내용
                        .build());
            }

            // 문제집 정보 저장
            List<FindOneQuizResponseDto> list2 = new ArrayList<>(quizs.size());
            FindOneQuizbookResponseDto response = FindOneQuizbookResponseDto.builder()
                    .isOwned(purchaseService.isPurchased(memberId, quizbookId))     // 문제집 보유 여부
                    .quizNum(quizbook.getNumberOfQuizs())                           // 총 문제 수
                    .build();
            response.setResult(findOneQuizResponseDtos);                            // 문제집에 문제 할당

            // 정상 응답
            return ResponseMapper.register(HttpStatus.OK, response);
        } return ResponseMapper.register(HttpStatus.BAD_REQUEST);      // 로그인 실패
    }
}
