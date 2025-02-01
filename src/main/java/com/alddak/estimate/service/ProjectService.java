package com.alddak.estimate.service;

import com.alddak.estimate.dto.ProjectCreateRequest;
import com.alddak.estimate.entity.Project;
import com.alddak.estimate.enums.Category;
import com.alddak.estimate.enums.ProjectStatus;
import com.alddak.estimate.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Transactional
    public void createProject(ProjectCreateRequest request) {
        if (request.getCategory() != null) {
            Project project = buildProject(request);
            projectRepository.save(project);
        } else {
            for (Category category : Category.values()) {
                Project project = buildProject(request);
                project.setCategory(category);
                projectRepository.save(project);
            }
        }
    }

    private Project buildProject(ProjectCreateRequest request) {
        return Project.builder()
                .projectType(request.getProjectType())
                .customProjectType(request.getCustomProjectType())
                .category(request.getCategory())
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