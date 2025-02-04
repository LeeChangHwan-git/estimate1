package com.storelabs.mygage.estimate.service;

import com.storelabs.mygage.estimate.dto.request.ProjectCreateRequest;
import com.storelabs.mygage.estimate.entity.Project;
import com.storelabs.mygage.estimate.entity.User;
import com.storelabs.mygage.estimate.enums.*;
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
    private final ProjectRequestService projectRequestService;

    // B2C 유저가 프로젝트를 생성
    // category는 list<Category> 형태이다.
    @Transactional
    public void createProject(ProjectCreateRequest request) {
        User user = getRequestUser(request.getUserId());
        // @TODO test 및 중복의 기준에 대해서 표준화 필요
        // 동일user, 동일projectType이면 중복처리
        checkDuplicateProject(user, request.getProjectType());

        if (request.getProjectStatusDetail() != null &&
                request.getProjectStatusDetail() == ProjectStatusDetail.BASIC_FORM_WRITING) {
            Project project = buildProject(request, user);
            projectRepository.save(project);

        } else if (request.getProjectStatusDetail() != null &&
                request.getProjectStatusDetail() == ProjectStatusDetail.BASIC_FORM_COMPLETED) {
            Project project = buildProject(request, user);f
            projectRepository.save(project);
            projectRequestService.createProjectRequest();
        }
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

    private void checkDuplicateProject(User user, ProjectType projectType) {
        if (projectRepository.existsByUserAndProjectType(user, projectType)) {
            throw new BusinessException(
                    ErrorCode.DUPLICATE_ESTIMATE_REQUEST,
                    user.getUserId(),
                    projectType.name()
            );
        }
    }

    public List<Project> findProjectsByUserId(String userId) {
        return projectRepository.findByUser_UserId(userId);
    }

    private Project buildProject(ProjectCreateRequest request, User user) {
        return Project.builder()
                .user(user)
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
                .projectStatusDetail(request.getProjectStatusDetail())
                .build();
    }
}