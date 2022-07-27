package com.kkamgi.quiz.controller;

import com.kkamgi.quiz.domain.Quizbook;
import com.kkamgi.quiz.dto.response.GetQuizbooksResponse;
import com.kkamgi.quiz.service.QuizbookService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/v1")
@RestController
public class QuizbookController {
    private final QuizbookService quizbookService;

    @ApiOperation(value = "문제집 리스트 조회", notes = "문제집 아이디, 가격, 보유 여부, 문제 수, 유형별 수, 키워드 리스트를 조회한다.")
    @ApiImplicitParam(name = "code", value = "로그인한 회원 코드", required = true, dataType = "String", paramType = "header", example = "0000")
    @GetMapping("/quizbooks")
    public ResponseMapper<List<GetQuizbooksResponse>> getQuizbooks(@RequestHeader(value = "code") String code) {
        return ResponseMapper.register(200, "OK", quizbookService.getQuizbooks());
    }

    @GetMapping("/quizbooks/{quizbookId}")
    public ResponseMapper<Quizbook> getQuizbook(@PathVariable(value = "quizbookId") Long quizbookId) {
        Optional<Quizbook> quizbook = quizbookService.getQuizbook(quizbookId);
        if (quizbook.isPresent()) {
            return ResponseMapper.register(200, "OK", quizbook.get());
        } else {
            return ResponseMapper.register(404, "Not Found", null);
        }
    }
}
