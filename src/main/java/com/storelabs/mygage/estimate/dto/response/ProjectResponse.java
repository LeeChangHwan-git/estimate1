package com.storelabs.mygage.estimate.dto.response;

import com.storelabs.mygage.estimate.entity.Project;
import com.storelabs.mygage.estimate.enums.Category;
import com.storelabs.mygage.estimate.enums.ProjectStatus;
import com.storelabs.mygage.estimate.enums.ProjectType;
import com.storelabs.mygage.estimate.enums.StartupType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ProjectResponse {
    private Long projectNo;
    private ProjectType projectType;
    private String customProjectType;
    private Category category;
    private ProjectStatus status;
    private LocalDate desiredDate;
    private String city;
    private String district;
    private String address;
    private String squareFootage;
    private String budget;
    private StartupType startupType;

    public static ProjectResponse from(Project project) {
        return ProjectResponse.builder()
                .projectNo(project.getProjectNo())
                .projectType(project.getProjectType())
                .customProjectType(project.getCustomProjectType())
                .category(project.getCategory())
                .status(project.getStatus())
                .desiredDate(project.getDesiredDate())
                .city(project.getCity())
                .district(project.getDistrict())
                .squareFootage(project.getSquareFootage())
                .budget(project.getBudget())
                .startupType(project.getStartupType())
                .build();
    }
}