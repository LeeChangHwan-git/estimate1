package com.alddak.estimate.controller;

import com.alddak.estimate.dto.ProjectCreateRequest;
import com.alddak.estimate.entity.Project;
import com.alddak.estimate.service.ExpertService;
import com.alddak.estimate.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

// Controller
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EstimateController {
    private final ProjectService projectService;
    private final ExpertService expertService;


    @PostMapping("/v1/estimates")
    public ResponseEntity<Project> createEstimate(@RequestBody ProjectCreateRequest request) {
        projectService.createProject(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/estimates/expert/{userId}")
    public ResponseEntity<List<Project>> findEstimateReqProjects(@PathVariable String userId) {
        List<Project> projects = expertService.findEstimateReqProjects(userId);
        return ResponseEntity.ok(projects);
    }
}