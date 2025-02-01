package com.alddak.estimate.entity;

import com.alddak.estimate.enums.UserType;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    private String id;
    private String password;
    private String name;
    private String mobile;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserType userType;

}
