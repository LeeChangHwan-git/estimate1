package com.storelabs.mygage.estimate.enums;

import lombok.Getter;

@Getter
public enum ProjectStatusDetail {
    BASIC_FORM_COMPLETED("기본요청서 작성완료"),
    DETAIL_FORM_REQUESTED("상세요청서 작성요청"),
    DETAIL_FORM_COMPLETED("상세요청서 작성완료");

    private final String korName;

    ProjectStatusDetail(String korName) {
        this.korName = korName;
    }

}
