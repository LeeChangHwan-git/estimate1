package com.storelabs.mygage.estimate.enums;

public enum RequestStatus {
    IN_PROGRESS("진행중"),
    EXPIRED("기간경과"),
    COMPLETED("최종완료");

    private final String korName;

    RequestStatus(String korName) {
        this.korName = korName;
    }
}
