package com.storelabs.mygage.estimate.enums;

import lombok.Getter;

@Getter
public enum ProjcetStatusDetail {
    BASIC_FORM_WRITING("기본요청서 작성중"),
    BASIC_FORM_COMPLETED("기본요청서 작성완료"),
    DETAIL_FORM_REQUESTED("상세요청서 작성 요청"),
    DETAIL_FORM_COMPLETED("상세요청서 작성완료");

    private final String korName;

    ProjcetStatusDetail(String korName) {
        this.korName = korName;
    }

}
