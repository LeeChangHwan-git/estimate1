package com.storelabs.mygage.estimate.dto;

import com.storelabs.mygage.estimate.enums.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ProjectDTO {
    private Long projectNo;
    private UserDTO user;
    private ProjectType projectType;
    private String customProjectType;
    private Category category;
    private ProjectStatus status;
    private ProjectStatusDetail projectStatusDetail;
    private LocalDate desiredDate;
    private String city;
    private String district;
    private String dong;
    private String squareFootage;
    private String budget;
    private StartupType startupType;
}
