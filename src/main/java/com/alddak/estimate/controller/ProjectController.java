package com.alddak.estimate.controller;

import com.alddak.estimate.dto.request.ProjectCreateRequest;
import com.alddak.estimate.entity.Project;
import com.alddak.estimate.service.ExpertService;
import com.alddak.estimate.service.ProjectService;
import com.alddak.estimate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final ExpertService expertService;
    private final UserService userService;

    @PostMapping("/v1/projects")
    public ResponseEntity<Project> createProject(@RequestBody ProjectCreateRequest request) {
        projectService.createProject(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/projects/expert/{userId}")
    public ResponseEntity<List<Project>> findEstimateReqProjects(@PathVariable String userId) {
        List<Project> expertProjects = expertService.findEstimateReqProjects(userId);
        return ResponseEntity.ok(expertProjects);
    }
}
