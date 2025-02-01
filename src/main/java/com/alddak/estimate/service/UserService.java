package com.alddak.estimate.service;

import com.alddak.estimate.entity.User;
import com.alddak.estimate.enums.ErrorCode;
import com.alddak.estimate.exception.BusinessException;
import com.alddak.estimate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND, userId));
    }
}
