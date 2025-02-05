package com.storelabs.mygage.estimate.service;

import com.storelabs.mygage.estimate.dto.request.ProjectCreateRequest;
import com.storelabs.mygage.estimate.dto.response.ProjectResponse;
import com.storelabs.mygage.estimate.entity.Project;
import com.storelabs.mygage.estimate.entity.User;
import com.storelabs.mygage.estimate.enums.*;
import com.storelabs.mygage.estimate.exception.BusinessException;
import com.storelabs.mygage.estimate.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserService userService;
    private final ProjectRequestService projectRequestService;
    private final UserFacadeService userFacadeService;

    // B2C 유저가 프로젝트를 생성
    // category는 list<Category> 형태이다.
    @Transactional
    public void createProject(ProjectCreateRequest request) {
        User user = getRequestUser(request.getUserId());
        projectAndRequestCreation(request, user);
    }

    @Transactional
    public void createProjectByJwt(ProjectCreateRequest request, String token) {
        User user = userFacadeService.getUserFromToken(token);
        projectAndRequestCreation(request, user);
    }

    private void projectAndRequestCreation(ProjectCreateRequest request, User user) {
        // 프로젝트 상태코드가 IN_PROGRESS인 프로젝트가 5개 넘으면 에러
        checkInProgressProjectCnt(user);

        // 동일user, 동일projectType, 동일 지역이면 중복처리
        if (!"Y".equals(request.getDuplicateConfirmYn())) {
            checkDuplicateProject(user, request.getProjectType(), request.getCity(), request.getDistrict(), request.getDong());
        }

        if (request.getProjectStatusDetail() != null &&
                request.getProjectStatusDetail() == ProjectStatusDetail.BASIC_FORM_COMPLETED) {
            Project project = buildProject(request, user);
            projectRepository.save(project);
            projectRequestService.createProjectRequest(project, request.getCategories());
        }
    }

    private User getRequestUser(String userId) {
        return userService.findUserById(userId);
    }

    private void checkDuplicateProject(User user, ProjectType projectType, String city, String district, String dong) {
        if (projectRepository.existsByUserAndProjectTypeAndCityAndDistrictAndDong(user, projectType, city, district, dong)) {
            throw new BusinessException(
                    ErrorCode.DUPLICATE_PROJECT_REQUEST,
                    user.getUserId(),
                    projectType.name(),
                    city,
                    district,
                    dong
            );
        }
    }

    private void checkInProgressProjectCnt(User user) {
        if (projectRepository.countByUserAndStatus(user, ProjectStatus.IN_PROGRESS) >= 5) {
            throw new BusinessException(ErrorCode.OVER_PROJECT_MAX_COUNT);
        }
    }

    @Transactional
    public List<ProjectResponse> findProjectsByUserId(String userId) {
        return projectRepository.findByUserIdWithEstimates(userId).stream()
                .map(ProjectResponse::from)
                .collect(Collectors.toList());
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
                .dong(request.getDong())
                .squareFootage(request.getSquareFootage())
                .budget(request.getBudget())
                .startupType(request.getStartupType())
                .projectStatusDetail(request.getProjectStatusDetail())
                .build();
    }
}