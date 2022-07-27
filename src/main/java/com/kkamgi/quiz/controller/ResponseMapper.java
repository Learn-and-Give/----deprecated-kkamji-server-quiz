package com.kkamgi.quiz.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ResponseMapper<T> {
    private int code;
    private String message;
    private T result;

    protected ResponseMapper(final int code, final String message) {
        this.code = code;
        this.message = message;
        this.result = null;
    }

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
}
