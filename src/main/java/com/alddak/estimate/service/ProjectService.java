package com.alddak.estimate.service;

import com.alddak.estimate.dto.request.ProjectCreateRequest;
import com.alddak.estimate.entity.Project;
import com.alddak.estimate.entity.User;
import com.alddak.estimate.enums.Category;
import com.alddak.estimate.enums.ErrorCode;
import com.alddak.estimate.enums.ProjectStatus;
import com.alddak.estimate.enums.ProjectType;
import com.alddak.estimate.exception.BusinessException;
import com.alddak.estimate.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserService userService;

    @Transactional
    public void createProject(ProjectCreateRequest request) {
        User user = getRequestUser(request.getUserId());

        if (request.getCategories() != null && !request.getCategories().isEmpty()) {
            // 요청에 포함된 카테고리들에 대해 프로젝트 생성
            for (Category category : request.getCategories()) {
                checkDuplicateEstimate(user, request.getProjectType(), category);
                Project project = buildProject(request);
                project.setCategory(category);
                project.setClient(user);
                projectRepository.save(project);
            }
        } else {
            // 카테고리가 지정되지 않은 경우 모든 카테고리에 대해 프로젝트 생성
            for (Category category : Category.values()) {
                checkDuplicateEstimate(user, request.getProjectType(), category);
                Project project = buildProject(request);
                project.setCategory(category);
                project.setClient(user);
                projectRepository.save(project);
            }
        }
    }

    private User getRequestUser(String userId) {
        return userService.findUserById(userId);
    }

    private void checkDuplicateEstimate(User user, ProjectType projectType, Category category) {
        if (projectRepository.existsByClientAndProjectTypeAndCategoryAndStatus(
                user,
                projectType,
                category,
                ProjectStatus.ESTIMATE_REQ
        )) {
            throw new BusinessException(
                    ErrorCode.DUPLICATE_ESTIMATE_REQUEST,
                    user.getId(),
                    projectType.name(),
                    category.name()
            );
        }
    }

    private Project buildProject(ProjectCreateRequest request) {
        return Project.builder()
                .projectType(request.getProjectType())
                .customProjectType(request.getCustomProjectType())
                // category는 위에서 개별적으로 설정하므로 여기서는 제외
                .status(ProjectStatus.ESTIMATE_REQ)
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