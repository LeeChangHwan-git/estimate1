package com.alddak.estimate.entity;

import com.alddak.estimate.enums.UserType;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "users", indexes = {
        @Index(name = "uk_user_id", columnList = "id", unique = true)
})
@Getter
public class User {

    @Id
    @Column(name = "no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    // 견적요청시 id를 가지고 no를 찾아야하므로 unique index 설정함
    @Column(unique = true)
    private String id;
    private String password;
    private String name;
    private String mobile;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserType userType;

}
