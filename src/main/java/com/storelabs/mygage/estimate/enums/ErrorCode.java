package com.storelabs.mygage.estimate.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // User 관련 에러
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found with ID: %s"),

    // Project 관련 에러
    DUPLICATE_PROJECT_REQUEST(
            HttpStatus.OK,
            "프로젝트 중복 요청 user ID: %s, project type: %s, address: %s %s %s"
    ),
    OVER_PROJECT_MAX_COUNT(
            HttpStatus.OK,
            "진행중인 알딱가게 프로젝트 수가 5개이상입니다. 프로젝트 종료후 재시도해주세요"
    ),

    // Common 에러
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "Invalid input value: %s"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error: %s");

    private final HttpStatus status;
    private final String message;

    public String getMessage(String... args) {
        return String.format(message, (Object[]) args);
    }
}
