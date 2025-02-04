package com.storelabs.mygage.estimate.controller;

import com.storelabs.mygage.estimate.dto.response.ProjectRequestResponse;
import com.storelabs.mygage.estimate.dto.response.ProjectResponse;
import com.storelabs.mygage.estimate.entity.ProjectRequest;
import com.storelabs.mygage.estimate.service.ProjectRequestService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProjectRequestController {
    private final ProjectRequestService projectRequestService;
    // Swagger
    @Operation(summary = "B2B - 프로젝트 요청서 목록 조회", description = "=프로젝트-카테고리 별 요청서 목록을 조회, userId 값을 pathvariable로 받는다")
    // GetMapping
    @GetMapping("/v1/projectRequests/{userId}")
    public ResponseEntity<List<ProjectRequestResponse>> getProjectRequests(@PathVariable String userId) {
        return ResponseEntity.ok(projectRequestService.findInProgressProjectRequests(userId));
    }
}
