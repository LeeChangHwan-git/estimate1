package com.alddak.estimate.service;

import com.alddak.estimate.entity.Project;
import com.alddak.estimate.entity.User;
import com.alddak.estimate.enums.ErrorCode;
import com.alddak.estimate.enums.ProjectStatus;
import com.alddak.estimate.exception.BusinessException;
import com.alddak.estimate.repository.ProjectRepository;
import com.alddak.estimate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public User findUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND, userId));
    }

    public List<Project> findUserProjects(String userId) {
        User user = findUserById(userId);
        return projectRepository.findByClientAndStatus(user, ProjectStatus.ESTIMATE_REQ);
    }

}
