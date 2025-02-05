package com.storelabs.mygage.estimate.service;

import com.storelabs.mygage.estimate.dto.request.EstimateCreateRequest;
import com.storelabs.mygage.estimate.dto.response.EstimateResponse;
import com.storelabs.mygage.estimate.entity.Estimate;
import com.storelabs.mygage.estimate.entity.FileEntity;
import com.storelabs.mygage.estimate.entity.ProjectRequest;
import com.storelabs.mygage.estimate.entity.User;
import com.storelabs.mygage.estimate.enums.ErrorCode;
import com.storelabs.mygage.estimate.enums.EstimateStatus;
import com.storelabs.mygage.estimate.exception.BusinessException;
import com.storelabs.mygage.estimate.repository.EstimateRepository;
import com.storelabs.mygage.estimate.repository.ProjectRequestRepository;
import com.storelabs.mygage.estimate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstimateService {
    private final EstimateRepository estimateRepository;
    private final ProjectRequestRepository projectRequestRepository;
    private final UserRepository userRepository;
    private final FileService fileService;

    @Transactional
    public EstimateResponse createEstimate(EstimateCreateRequest request) {
        ProjectRequest projectRequest = projectRequestRepository.findById(request.getProjectRequestId())
                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_INPUT_VALUE, "ProjectRequest not found"));

        User user = projectRequest.getProject().getUser();
        if (user == null) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND, "User not found for the given ProjectRequest");
        }

        // 3. 견적 엔티티 생성
        Estimate estimate = Estimate.builder()
                .projectRequest(projectRequest)
                .estimateAmt(request.getEstimateAmt())
                .detailedDescription(request.getDescription())
                .estimateStatus(EstimateStatus.ESTIMATE_REQ)
                .build();

        // 4. 파일 연결
        if (request.getFileIds() != null && !request.getFileIds().isEmpty()) {
            List<FileEntity> files = fileService.getFilesByIds(request.getFileIds());
            files.forEach(estimate::addFile);
        }

        // 5. 견적 저장 및 응답 생성
        Estimate savedEstimate = estimateRepository.save(estimate);
        return EstimateResponse.from(savedEstimate);
    }
}
