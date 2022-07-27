package com.kkamgi.quiz.dto.response;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class GetQuizbooksResponse {

    @ApiModelProperty(value = "이름", example = "1")
    private final Long quizBookID;

    @ApiModelProperty(value = "이름", example = "20")
    private final Integer quizBookPrice;

    private final Integer isOwned;

    @ApiModelProperty(value = "문제 수", example = "10")
    private final int quizCnt;

    @ApiModelProperty(value = "객관식 문제 수", example = "2")
    private final Integer choiceQuizCnt;

    @ApiModelProperty(value = "단답형 문제 수", example = "4")
    private final Integer shortQuizCnt;

    @ApiModelProperty(value = "서술형 문제 수", example = "[\"자바\", \"서술형\", \"객체지향\", \"플랫폼\", \"ERD\", ...]")
    private final Integer longQuizCnt;

    @ApiModelProperty(value = "키워드", example = "4")
    private final List<String> keywords;
}
