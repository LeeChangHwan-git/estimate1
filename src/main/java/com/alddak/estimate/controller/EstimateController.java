package com.alddak.estimate.controller;

import com.alddak.estimate.dto.ProjectCreateRequest;
import com.alddak.estimate.entity.Project;
import com.alddak.estimate.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

// Controller
@RestController
@RequestMapping("/api/v1")
public class EstimateController {
    private final ProjectService projectService;

    public EstimateController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/estimates")
    public ResponseEntity<Project> createEstimate(@RequestBody ProjectCreateRequest request) {
        projectService.createProject(request);
        return ResponseEntity.ok().build();

    }
}