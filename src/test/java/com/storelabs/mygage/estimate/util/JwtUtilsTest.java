package com.storelabs.mygage.estimate.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtUtilsTest {
    @Autowired
    private JwtUtils jwtUtils;

    @Test
    void generateTokenTest() {
        String token = jwtUtils.generateToken("client1");
        System.out.println("token = " + token);
    }
}