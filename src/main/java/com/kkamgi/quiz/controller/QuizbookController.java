package com.kkamgi.quiz.controller;

import com.kkamgi.quiz.domain.Quizbook;
import com.kkamgi.quiz.dto.response.GetQuizbooksResponse;
import com.kkamgi.quiz.service.MemberService;
import com.kkamgi.quiz.service.QuizbookService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/v1")
@RestController
public class QuizbookController {
    private final QuizbookService quizbookService;
    private final MemberService memberService;

    @ApiOperation(value = "문제집 리스트 조회", notes = "문제집 아이디, 가격, 보유 여부, 문제 수, 유형별 수, 키워드 리스트를 조회한다.")
    @ApiImplicitParam(name = "code", value = "로그인한 회원 코드", required = true, dataType = "String", paramType = "header", example = "0000")
    @GetMapping("/quizbooks")
    public ResponseMapper<List<GetQuizbooksResponse>> getQuizbooks(@RequestHeader(value = "code") String code) {
        if (memberService.existsByPassword(code)) {
            Long memberId = memberService.findOne(code).get().getId();
            return ResponseMapper.register(HttpStatus.OK, quizbookService.getQuizbooks(memberId));
        } else return ResponseMapper.register(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/quizbooks/{quizbookId}")
    public ResponseMapper<Quizbook> getQuizbook(@PathVariable(value = "quizbookId") Long quizbookId) {
        Optional<Quizbook> quizbook = quizbookService.getQuizbook(quizbookId);
        if (quizbook.isPresent()) return ResponseMapper.register(HttpStatus.OK, quizbook.get());
        else return ResponseMapper.register(HttpStatus.NOT_FOUND);
    }
}
