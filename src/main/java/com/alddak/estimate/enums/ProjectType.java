package com.alddak.estimate.enums;

// Enums
public enum ProjectType {
    CAFE("카페"),
    ICE_CREAM_SHOP("아이스크림점"),
    OTHER("기타");

    private final String korName;

    ProjectType(String korName) {
        this.korName = korName;
    }

    public String getKorName() {
        return korName;
    }
}