package com.storelabs.mygage.estimate.service;

import com.storelabs.mygage.estimate.dto.response.ProjectRequestResponse;
import com.storelabs.mygage.estimate.entity.ExpertCategory;
import com.storelabs.mygage.estimate.entity.Project;
import com.storelabs.mygage.estimate.entity.ProjectRequest;
import com.storelabs.mygage.estimate.entity.User;
import com.storelabs.mygage.estimate.enums.Category;
import com.storelabs.mygage.estimate.enums.ProjectRequestStatus;
import com.storelabs.mygage.estimate.enums.ProjectStatus;
import com.storelabs.mygage.estimate.repository.ExpertCategoryRepository;
import com.storelabs.mygage.estimate.repository.ProjectRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectRequestService {
    private final ProjectRequestRepository projectRequestRepository;
    private final ExpertCategoryRepository expertCategoryRepository;

    public void createProjectRequest(Project project, List<Category> categoryList) {
        List<ProjectRequest> projectRequests = categoryList.stream()
                .map(category -> buildProjectRequest(project, category))
                .collect(Collectors.toList());

        projectRequestRepository.saveAll(projectRequests);
    }

    private ProjectRequest buildProjectRequest(Project project, Category category) {
        return ProjectRequest.builder()
                .project(project)
                .category(category)
                .projectRequestStatus(ProjectRequestStatus.ESTIMATE_REQUESTED)
                .build();
    }

    /**
     * 전문가 ID를 받아서 해당 전문가가 수행할 수 있는 프로젝트 요청 목록을 조회한다.
     * - 전문가의 카테고리와 일치하는 요청만 조회
     * - 프로젝트 상태가 IN_PROGRESS인 요청만 조회
     *
     * @param expertId 전문가 ID
     * @return 수행 가능한 ProjectRequest 목록
     */
    public List<ProjectRequestResponse> findInProgressProjectRequests(String expertId) {
        // 1. 전문가의 카테고리 목록 조회
        List<ExpertCategory> expertCategories = expertCategoryRepository.findByIdUserId(expertId);

        // 2. 전문가의 카테고리 목록 추출
        List<Category> categories = expertCategories.stream()
                .map(ec -> ec.getId().getCategory())
                .collect(Collectors.toList());

        // 3. 조건에 맞는 ProjectRequest 조회
        // - 프로젝트 상태가 IN_PROGRESS
        // - 요청의 카테고리가 전문가의 카테고리 중 하나와 일치
        // - 견적이 없는 요청만 조회
        return projectRequestRepository.findByProjectStatusAndCategoryInWithoutEstimates(ProjectStatus.IN_PROGRESS, categories, expertId).stream()
                .map(ProjectRequestResponse::from)
                .collect(Collectors.toList());
    }
}
