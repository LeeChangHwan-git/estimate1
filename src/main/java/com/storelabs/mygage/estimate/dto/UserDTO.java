package com.storelabs.mygage.estimate.dto;

import com.storelabs.mygage.estimate.enums.UserType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDTO {
    private Long userNo;
    private String userId;
    private String password;
    private String name;
    private String mobile;
    private String email;
    private UserType userType;
    private String roleAt;
    private String userStateCode;
    private String refreshToken;
    private String roles;
    private String avatar;
    private String fcmKey;
}
