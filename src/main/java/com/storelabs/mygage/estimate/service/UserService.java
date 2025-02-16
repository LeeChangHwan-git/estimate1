package com.storelabs.mygage.estimate.service;

import com.storelabs.mygage.estimate.entity.Project;
import com.storelabs.mygage.estimate.entity.User;
import com.storelabs.mygage.estimate.enums.ErrorCode;
import com.storelabs.mygage.estimate.enums.ProjectStatus;
import com.storelabs.mygage.estimate.exception.BusinessException;
import com.storelabs.mygage.estimate.repository.ProjectRepository;
import com.storelabs.mygage.estimate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public User findUserById(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND, userId));
    }

    public List<Project> findUserProjects(String userId) {
        User user = findUserById(userId);
        return projectRepository.findByUserAndStatus(user, ProjectStatus.IN_PROGRESS);
    }

}
