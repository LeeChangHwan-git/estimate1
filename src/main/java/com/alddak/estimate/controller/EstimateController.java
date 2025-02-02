package com.alddak.estimate.controller;

import com.alddak.estimate.dto.request.EstimateCreateRequest;
import com.alddak.estimate.dto.request.ProjectCreateRequest;
import com.alddak.estimate.dto.response.EstimateResponse;
import com.alddak.estimate.entity.Project;
import com.alddak.estimate.service.EstimateService;
import com.alddak.estimate.service.ExpertService;
import com.alddak.estimate.service.ProjectService;
import com.alddak.estimate.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Swagger
@Tag(name = "견적 API", description = "견적 관련 API")
// Controller
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EstimateController {
    private final EstimateService estimateService;

    // Swagger
    @Operation(summary = "사용자 - 견적 목록 조회", description = "사용자의 견적 목록을 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음")
    })
    // GetMapping
    @GetMapping("/v1/users/{userId}/estimates")
    public ResponseEntity<List<EstimateResponse>> findUserProjectEstimates(
            @Parameter(description = "사용자 ID", required = true) @PathVariable String userId) {
        List<EstimateResponse> estimates = estimateService.findEstimatesByUserId(userId);
        return ResponseEntity.ok(estimates);
    }

    // Swagger
    @Operation(summary = "견적 생성", description = "새로운 견적을 생성")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "견적 생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "404", description = "프로젝트를 찾을 수 없음")
    })
    // PostMapping
    @PostMapping("/v1/estimates")
    public ResponseEntity<EstimateResponse> createEstimate(@RequestBody EstimateCreateRequest request) {
        EstimateResponse response = estimateService.createEstimate(request);
        return ResponseEntity.ok(response);
    }
}