package com.storelabs.mygage.estimate.enums;

public enum EstimateStatus {
    ESTIMATE_COMPLETED("견적완료"),
    CONSULTING_IN_PROGRESS("상담진행중"),
    SERVICE_COMPLETED("서비스완료");

    private final String korName;

    EstimateStatus(String korName) {
        this.korName = korName;
    }

    public String getKorName() {
        return korName;
    }
}
