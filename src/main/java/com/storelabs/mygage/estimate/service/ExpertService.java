package com.storelabs.mygage.estimate.service;

import com.storelabs.mygage.estimate.entity.Project;
import com.storelabs.mygage.estimate.entity.User;
import com.storelabs.mygage.estimate.enums.Category;
import com.storelabs.mygage.estimate.enums.ProjectStatus;
import com.storelabs.mygage.estimate.repository.ExpertCategoryRepository;
import com.storelabs.mygage.estimate.repository.ProjectRepository;
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

//    @Transactional
//    public List<Project> findEstimateReqProjects(String userId) {
//        User expert = userService.findUserById(userId);
//
//        List<Category> expertCategories = expertCategoryRepository.findCategoriesByUserNo(expert.getNo());
//
//        return projectRepository.findByStatusAndCategoryIn(ProjectStatus.ESTIMATE_REQ, expertCategories);
//    }

}
