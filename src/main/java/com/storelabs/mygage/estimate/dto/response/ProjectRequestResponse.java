package com.storelabs.mygage.estimate.dto.response;

import com.storelabs.mygage.estimate.dto.ProjectDTO;
import com.storelabs.mygage.estimate.dto.UserDTO;
import com.storelabs.mygage.estimate.entity.ProjectRequest;
import com.storelabs.mygage.estimate.enums.Category;
import com.storelabs.mygage.estimate.enums.ProjectRequestStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProjectRequestResponse {
    private Long id;
    private ProjectDTO project;
    private Category category;
    private ProjectRequestStatus projectRequestStatus;

    public static ProjectRequestResponse from(ProjectRequest projectRequest) {
        return ProjectRequestResponse.builder()
                .id(projectRequest.getId())
                .project(ProjectDTO.builder()
                        .projectNo(projectRequest.getProject().getProjectNo())
                        .user(UserDTO.builder()
                                .userId(projectRequest.getProject().getUser().getUserId())
                                .password(projectRequest.getProject().getUser().getPassword())
                                .name(projectRequest.getProject().getUser().getName())
                                .mobile(projectRequest.getProject().getUser().getMobile())
                                .email(projectRequest.getProject().getUser().getEmail())
                                .userType(projectRequest.getProject().getUser().getUserType())
                                .build())
                        .projectType(projectRequest.getProject().getProjectType())
                        .customProjectType(projectRequest.getProject().getCustomProjectType())
                        .category(projectRequest.getProject().getCategory())
                        .status(projectRequest.getProject().getStatus())
                        .projectStatusDetail(projectRequest.getProject().getProjectStatusDetail())
                        .desiredDate(projectRequest.getProject().getDesiredDate())
                        .city(projectRequest.getProject().getCity())
                        .district(projectRequest.getProject().getDistrict())
                        .dong(projectRequest.getProject().getDong())
                        .squareFootage(projectRequest.getProject().getSquareFootage())
                        .budget(projectRequest.getProject().getBudget())
                        .startupType(projectRequest.getProject().getStartupType())
                        .build())
                .category(projectRequest.getCategory())
                .projectRequestStatus(projectRequest.getProjectRequestStatus())
                .build();
    }
}
