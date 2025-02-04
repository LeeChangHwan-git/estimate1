package com.storelabs.mygage.estimate.enums;

import lombok.Getter;

// Enums
@Getter
public enum ProjectType {
    UNMANNED_CAFE("무인카페"),
    UNMANNED_ICECREAM("무인아이스크림"),
    OTHER("기타");

    private final String korName;

    ProjectType(String korName) {
        this.korName = korName;
    }

}