package com.storelabs.mygage.estimate.enums;

public enum ProjectStatus {
    ESTIMATE_REQ("견적요청"),
    ESTIMATE_DONE("견적완료"),
    DETAIL_REQ("상세견적요청"),
    DETAIL_DONE("상세견적완료"),
    COMPLETED("최종완료");

    private final String korName;

    ProjectStatus(String korName) {
        this.korName = korName;
    }

    public String getKorName() {
        return korName;
    }
}

