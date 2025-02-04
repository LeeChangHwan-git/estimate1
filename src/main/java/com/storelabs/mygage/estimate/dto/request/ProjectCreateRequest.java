package com.storelabs.mygage.estimate.dto.request;

import com.storelabs.mygage.estimate.enums.Category;
import com.storelabs.mygage.estimate.enums.ProjectStatusDetail;
import com.storelabs.mygage.estimate.enums.ProjectType;
import com.storelabs.mygage.estimate.enums.StartupType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

// DTO
@Getter
@NoArgsConstructor
public class ProjectCreateRequest {
    private String userId;
    private ProjectType projectType;
    private String customProjectType;
    private List<Category> categories;
    private LocalDate desiredDate;
    private String city;
    private String district;
    private String dong;
    private String squareFootage;
    private String budget;
    private StartupType startupType;
    private ProjectStatusDetail projectStatusDetail;
    private String duplicateConfirmYn;
}
