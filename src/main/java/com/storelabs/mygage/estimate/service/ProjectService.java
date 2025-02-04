package com.storelabs.mygage.estimate.service;

import com.storelabs.mygage.estimate.dto.request.ProjectCreateRequest;
import com.storelabs.mygage.estimate.entity.Project;
import com.storelabs.mygage.estimate.entity.User;
import com.storelabs.mygage.estimate.enums.Category;
import com.storelabs.mygage.estimate.enums.ErrorCode;
import com.storelabs.mygage.estimate.enums.ProjectStatus;
import com.storelabs.mygage.estimate.enums.ProjectType;
import com.storelabs.mygage.estimate.exception.BusinessException;
import com.storelabs.mygage.estimate.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserService userService;

    // B2C 유저가 프로젝트를 생성
    // category는 list<Category> 형태이다.
    @Transactional
    public void createProject(ProjectCreateRequest request) {
        User user = getRequestUser(request.getUserId());

        if (request.getCategories() != null && !request.getCategories().isEmpty()) {

            // 요청에 포함된 카테고리들에 대해 프로젝트 생성
            for (Category category : request.getCategories()) {
                // @TODO test 및 중복의 기준에 대해서 표준화 필요
                // 동일 userId, ProjectType, category가 존재하면 중복
                checkDuplicateEstimate(user, request.getProjectType(), category);
                Project project = buildProject(request);
                project.setCategory(category);
                project.setUser(user);
                projectRepository.save(project);
            }
        } else {
            // 카테고리가 지정되지 않은 경우 모든 카테고리에 대해 프로젝트 생성
            for (Category category : Category.values()) {
                checkDuplicateEstimate(user, request.getProjectType(), category);
                Project project = buildProject(request);
                project.setCategory(category);
                project.setUser(user);
                projectRepository.save(project);
            }
        }
    }

    private User getRequestUser(String userId) {
        return userService.findUserById(userId);
    }

    private void checkDuplicateEstimate(User user, ProjectType projectType, Category category) {
        if (projectRepository.existsByUserAndProjectTypeAndCategoryAndStatus(
                user,
                projectType,
                category,
                ProjectStatus.IN_PROGRESS
        )) {
            throw new BusinessException(
                    ErrorCode.DUPLICATE_ESTIMATE_REQUEST,
                    user.getUserId(),
                    projectType.name(),
                    category.name()
            );
        }
    }

    public List<Project> findProjectsByUserId(String userId) {
        return projectRepository.findByUser_UserId(userId);
    }

    private Project buildProject(ProjectCreateRequest request) {
        return Project.builder()
                .projectType(request.getProjectType())
                .customProjectType(request.getCustomProjectType())
                // category는 위에서 개별적으로 설정하므로 여기서는 제외
                .status(ProjectStatus.IN_PROGRESS)
                .desiredDate(request.getDesiredDate())
                .city(request.getCity())
                .district(request.getDistrict())
                .address(request.getAddress())
                .squareFootage(request.getSquareFootage())
                .budget(request.getBudget())
                .startupType(request.getStartupType())
                .build();
    }
}