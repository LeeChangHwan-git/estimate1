package com.storelabs.mygage.estimate.dto;

import com.storelabs.mygage.estimate.enums.UserType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDTO {
    private String userId;
    private String password;
    private String name;
    private String mobile;
    private String email;
    private UserType userType;
}
