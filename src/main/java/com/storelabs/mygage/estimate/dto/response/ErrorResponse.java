package com.storelabs.mygage.estimate.dto.response;

import com.storelabs.mygage.estimate.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {    private final String code;
    private final String message;

    public static ErrorResponse of(ErrorCode errorCode, String message) {
        return new ErrorResponse(errorCode.name(), message);
    }

}
