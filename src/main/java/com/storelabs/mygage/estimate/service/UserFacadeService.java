package com.storelabs.mygage.estimate.service;

import com.storelabs.mygage.estimate.entity.User;
import com.storelabs.mygage.estimate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserFacadeService {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @Transactional
    public User getUserFromToken(String token) {
        String userIdFromToken = authenticationService.getUserIdFromToken(token);
        return userService.findUserById(userIdFromToken);
    }

}
