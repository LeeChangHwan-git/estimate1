package com.storelabs.mygage.estimate.enums;

import lombok.Getter;

@Getter
public enum ProjectRequestStatus {
    ESTIMATE_REQUESTED("견적요청"),
    DETAIL_REQUESTED("상세견적요청"),
    USER_TERMINATED("사용자요청종료"),
    EXPIRED("기간만료종료"),
    FINAL_COMPLETED("최종완료");
    private final String korName;

    ProjectRequestStatus(String korName) {
        this.korName = korName;
    }
}
