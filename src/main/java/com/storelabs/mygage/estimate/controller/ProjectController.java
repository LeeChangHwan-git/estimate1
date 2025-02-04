package com.storelabs.mygage.estimate.controller;

import com.storelabs.mygage.estimate.dto.request.ProjectCreateRequest;
import com.storelabs.mygage.estimate.dto.response.ProjectResponse;
import com.storelabs.mygage.estimate.entity.Project;
import com.storelabs.mygage.estimate.service.ProjectFacadeService;
import com.storelabs.mygage.estimate.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectFacadeService projectFacadeService;

    // 회원가입과 동시에 Project 생성요청을 날릴때 사용한다.
    @PostMapping("/v1/projects/create")
    public ResponseEntity<Project> createProject(@RequestBody ProjectCreateRequest request) {
        projectService.createProject(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/v2/projects/create")
    public ResponseEntity<Project> createProjectByJwt(@RequestBody ProjectCreateRequest request, @RequestHeader("Authorization") String token) {
        projectService.createProjectByJwt(request, token);
        return ResponseEntity.ok().build();
    }

    // Swagger
    @Operation(summary = "사용자 - 프로젝트 목록 조회", description = "사용자의 프로젝트 목록을 조회, userId 값을 pathvariable로 받는다")
    // GetMapping
    @GetMapping("/v1/projects/{userId}")
    public ResponseEntity<List<ProjectResponse>> getProjects(@PathVariable String userId) {
        return ResponseEntity.ok(projectService.findProjectsByUserId(userId));
    }

    // Swagger
    @Operation(summary = "사용자 - 프로젝트 목록 조회", description = "사용자의 프로젝트 목록을 조회, userId 값은 http header Authorization으로 받는다")
    // GetMapping
    @GetMapping("/v2/projects")
    public ResponseEntity<List<ProjectResponse>> getProjectsByJwt(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(projectFacadeService.findProjectsByUserIdByJwt(token));
    }

}
