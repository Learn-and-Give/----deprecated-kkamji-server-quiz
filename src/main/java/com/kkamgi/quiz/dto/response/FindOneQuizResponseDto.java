package com.kkamgi.quiz.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Getter
@Builder
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class FindOneQuizResponseDto {

    @ApiModelProperty(value = "문제 아이디", example = "1")
    private final Long quizID;

    @ApiModelProperty(value = "문제 타입", example = "SINGLE_CHOICE")
    private final String quizType;

    @ApiModelProperty(value = "문제 풀었는지 여부", example = "1")
    private final boolean isSolved;

    @ApiModelProperty(value = "문제 내용", example = "1")
    private final Map<String, Object> quizContent;
}
