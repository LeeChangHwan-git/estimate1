package com.storelabs.mygage.estimate.service;

import com.storelabs.mygage.estimate.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtUtils jwtUtils;

    public String getUserIdFromToken(String token) {
        return jwtUtils.getUserIdFromToken(token);
    }
}
