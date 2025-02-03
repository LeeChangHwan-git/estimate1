package com.storelabs.mygage.estimate.controller;

import com.storelabs.mygage.estimate.dto.request.ProjectCreateRequest;
import com.storelabs.mygage.estimate.dto.response.ProjectResponse;
import com.storelabs.mygage.estimate.entity.Project;
import com.storelabs.mygage.estimate.service.AuthenticationService;
import com.storelabs.mygage.estimate.service.ExpertService;
import com.storelabs.mygage.estimate.service.ProjectService;
import com.storelabs.mygage.estimate.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final AuthenticationService authenticationService;

    @PostMapping("/v1/projects")
    public ResponseEntity<Project> createProject(@RequestBody ProjectCreateRequest request) {
        projectService.createProject(request);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/v1/projects/expert/{userId}")
//    public ResponseEntity<List<Project>> findEstimateReqProjects(@PathVariable String userId) {
//        List<Project> expertProjects = expertService.findEstimateReqProjects(userId);
//        return ResponseEntity.ok(expertProjects);
//    }

    // Swagger
    @Operation(summary = "사용자 - 프로젝트 목록 조회", description = "사용자의 프로젝트 목록을 조회, userId 값을 pathvariable로 받는다")
    // GetMapping
    @GetMapping("/v1/projects/{userId}")
    public ResponseEntity<List<ProjectResponse>> getProjects(@PathVariable String userId) {
        List<Project> userProjects = projectService.findProjectsByUserId(userId);
        List<ProjectResponse> responses = userProjects.stream()
                .map(ProjectResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    // Swagger
    @Operation(summary = "사용자 - 프로젝트 목록 조회", description = "사용자의 프로젝트 목록을 조회, userId 값은 http header Authorization으로 받는다")
    // GetMapping
    @GetMapping("/v2/projects")
    public ResponseEntity<List<ProjectResponse>> getProjectsByJwt(@RequestHeader("Authorization") String token) {
        String userIdFromToken = authenticationService.getUserIdFromToken(token);
        List<Project> userProjects = projectService.findProjectsByUserId(userIdFromToken);
        List<ProjectResponse> responses = userProjects.stream()
                .map(ProjectResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

}
