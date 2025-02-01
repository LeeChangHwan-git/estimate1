package com.alddak.estimate.enums;

// Enums
public enum ProjectType {
    UNMANNED_CAFE("무인카페"),
    UNMANNED_ICECREAM("무인아이스크림"),
    OTHER("기타");

    private final String korName;

    ProjectType(String korName) {
        this.korName = korName;
    }

    public String getKorName() {
        return korName;
    }
}