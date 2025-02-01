package com.alddak.estimate.dto;

import com.alddak.estimate.enums.Category;
import com.alddak.estimate.enums.ProjectType;
import com.alddak.estimate.enums.StartupType;
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
    private String address;
    private String squareFootage;
    private String budget;
    private StartupType startupType;
}
