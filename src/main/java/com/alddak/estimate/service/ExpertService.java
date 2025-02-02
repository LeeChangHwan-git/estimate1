package com.alddak.estimate.service;

import com.alddak.estimate.entity.Project;
import com.alddak.estimate.entity.User;
import com.alddak.estimate.enums.Category;
import com.alddak.estimate.enums.ProjectStatus;
import com.alddak.estimate.repository.ExpertCategoryRepository;
import com.alddak.estimate.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpertService {
    private final UserService userService;
    private final ExpertCategoryRepository expertCategoryRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    public List<Project> findEstimateReqProjects(String userId) {
        User expert = userService.findUserById(userId);

        List<Category> expertCategories = expertCategoryRepository.findCategoriesByUserNo(expert.getNo());

        return projectRepository.findByStatusAndCategoryIn(ProjectStatus.ESTIMATE_REQ, expertCategories);
    }

}
