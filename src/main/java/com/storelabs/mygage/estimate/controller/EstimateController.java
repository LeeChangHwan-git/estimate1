package com.storelabs.mygage.estimate.controller;

import com.storelabs.mygage.estimate.dto.request.EstimateCreateRequest;
import com.storelabs.mygage.estimate.dto.response.EstimateResponse;
import com.storelabs.mygage.estimate.service.EstimateService;
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

//    // Swagger
//    @Operation(summary = "사용자 - 견적 목록 조회", description = "사용자의 견적 목록을 조회")
//    // GetMapping
//    @GetMapping("/v1/users/{userId}/estimates")
//    public ResponseEntity<List<EstimateResponse>> findUserProjectEstimates(
//            @Parameter(description = "사용자 ID", required = true) @PathVariable String userId) {
//        List<EstimateResponse> estimates = estimateService.findEstimatesByUserId(userId);
//        return ResponseEntity.ok(estimates);
//    }

    // Swagger
    @Operation(summary = "견적 생성", description = "새로운 견적을 생성")
    // PostMapping
    @PostMapping("/v1/estimates/create")
    public ResponseEntity<EstimateResponse> createEstimate(@RequestBody EstimateCreateRequest request) {
        EstimateResponse response = estimateService.createEstimate(request);
        return ResponseEntity.ok(response);
    }
}