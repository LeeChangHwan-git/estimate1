package com.alddak.estimate.enums;

import lombok.Getter;

@Getter
public enum Category {
    FACILITY("시설"),
    ELECTRICAL("전기"),
    CLEAN("청소");

    private final String korName;

    Category(String korName) {
        this.korName = korName;
    }

}