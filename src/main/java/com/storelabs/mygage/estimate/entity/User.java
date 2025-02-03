package com.storelabs.mygage.estimate.entity;

import com.storelabs.mygage.estimate.enums.UserType;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "users")
@Getter
public class User extends BaseTimeEntity {
    @Id
    private String userId;
    private String password;
    private String name;
    private String mobile;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserType userType;

}
