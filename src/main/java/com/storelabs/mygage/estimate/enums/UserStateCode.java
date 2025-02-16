package com.storelabs.mygage.estimate.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum UserStateCode {
    // toDo
    // 탈퇴상태에서 일정 기간이후 삭제를 통해서 개인정보를 제거하는 방향
    WAIT("00", "대기"),
    NORMAL("01", "정상"),
    HALT("07", "정지"),
    LEAVE("08", "탈퇴"),
    DELETE("09", "삭제");

    private final String key;
    private final String title;

    /**
     * 사용자 상태 코드로 상수 검색
     *
     * @param key 사용자 상태 코드
     * @return UserStateCode 사용자 상태 코드 상수
     */
    public static UserStateCode findByKey(String key) {
        return Arrays.stream(UserStateCode.values()).filter(c -> c.getKey().equals(key)).findAny().orElse(null);
    }

}
