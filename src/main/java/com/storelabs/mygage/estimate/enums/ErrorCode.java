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
    DUPLICATE_ESTIMATE_REQUEST(
            HttpStatus.CONFLICT,
            "견적 중복 요청 user ID: %s, project type: %s, category: %s"
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
