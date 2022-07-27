package com.kkamgi.quiz.controller;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ResponseMapper<T> {
    @ApiModelProperty(value = "상태", example = "200")
    private int code;
    @ApiModelProperty(value = "메시지", example = "OK")
    private String message;
    private T result;

    protected ResponseMapper(final int code, final String message) {
        this.code = code;
        this.message = message;
        this.result = null;
    }

    public static<T> ResponseMapper<T> register(HttpStatus status) {
        return register(status, null);
    }

    public static<T> ResponseMapper<T> register(HttpStatus status, final T t) {
        return ResponseMapper.<T>builder()
                .result(t)
                .code(status.value())
                .message(status.getReasonPhrase())
                .build();
    }
}

/*
public static<T> ResponseMapper<T> register(final int statusCode, final String responseMessage) {
        return register(statusCode, responseMessage, null);
    }

    public static<T> ResponseMapper<T> register(final int statusCode, final String responseMessage, final T t) {
        return ResponseMapper.<T>builder()
                .result(t)
                .code(statusCode)
                .message(responseMessage)
                .build();
    }
 */