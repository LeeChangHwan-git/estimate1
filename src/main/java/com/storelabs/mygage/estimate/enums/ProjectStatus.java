package com.storelabs.mygage.estimate.enums;

import lombok.Getter;

@Getter
public enum ProjectStatus {
    IN_PROGRESS("진행중"),
    EXPIRED("기간경과"),
    COMPLETED("최종완료");

    private final String korName;

    ProjectStatus(String korName) {
        this.korName = korName;
    }

}

