package com.kkamgi.quiz.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class FindOneQuizbookResponseDto {

    @ApiModelProperty(value = "포함된 문제들")
    private final List<FindOneQuizResponseDto> result = new ArrayList<>();

    @ApiModelProperty(value = "보유한 문제집인지 여부", example = "1")
    private final boolean isOwned;

    @ApiModelProperty(value = "총 문제 수", example = "1")
    private final Integer quizNum;

    public void setResult(List<FindOneQuizResponseDto> result) {
        this.result.addAll(result);
    }
}
