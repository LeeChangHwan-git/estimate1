package com.storelabs.mygage.estimate.service;

import com.storelabs.mygage.estimate.dto.request.EstimateCreateRequest;
import com.storelabs.mygage.estimate.dto.response.EstimateResponse;
import com.storelabs.mygage.estimate.entity.*;
import com.storelabs.mygage.estimate.enums.ErrorCode;
import com.storelabs.mygage.estimate.enums.EstimateStatus;
import com.storelabs.mygage.estimate.exception.BusinessException;
import com.storelabs.mygage.estimate.repository.EstimateRepository;
import com.storelabs.mygage.estimate.repository.ExpertEstimateDailyCountRepository;
import com.storelabs.mygage.estimate.repository.ExpertInfoRepository;
import com.storelabs.mygage.estimate.repository.ProjectRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EstimateService {
    private final EstimateRepository estimateRepository;
    private final ProjectRequestRepository projectRequestRepository;
    private final FileService fileService;
    private final ExpertInfoRepository expertInfoRepository;
    private final ExpertEstimateDailyCountRepository expertEstimateDailyCountRepository;

    @Transactional
    public EstimateResponse createEstimate(EstimateCreateRequest request) {
        ProjectRequest projectRequest = projectRequestRepository.findById(request.getProjectRequestId())
                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_INPUT_VALUE, "ProjectRequest not found"));

        User user = projectRequest.getProject().getUser();
        if (user == null) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND);
        }

        ExpertInfo expertInfo = expertInfoRepository.findByUser_UserId(request.getUserId());
        if (expertInfo == null) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND);
        }

        // 일일 견적 수 체크 및 업데이트
        LocalDate today = LocalDate.now();

        ExpertEstimateDailyCount dailyCount = expertEstimateDailyCountRepository.findByExpertAndUseDate(expertInfo.getUser(), today)
                .orElseGet(() -> new ExpertEstimateDailyCount(expertInfo.getUser(), today, 5));


        if (!dailyCount.canCreateEstimate()) {
            throw new BusinessException(ErrorCode.OVER_ESTIMATE_MAX_COUNT);
        }

        dailyCount.incrementCount();
        expertEstimateDailyCountRepository.save(dailyCount);

        // 3. 견적 엔티티 생성
        Estimate estimate = Estimate.builder()
                .expertInfo(expertInfo)
                .projectRequest(projectRequest)
                .estimateAmt(request.getEstimateAmt())
                .detailedDescription(request.getDescription())
                .estimateStatus(EstimateStatus.ESTIMATE_COMPLETED)
                .build();

        // 4. 파일 연결
        // 파일 처리 부분에 로그 추가
        if (request.getFileIds() != null && !request.getFileIds().isEmpty()) {
            List<FileEntity> files = fileService.getFilesByIds(request.getFileIds());

            // null check 추가
            files.stream()
                    .filter(Objects::nonNull)
                    .forEach(file -> {
                        estimate.addFile(file);
                    });
        }

        // 5. 견적 저장 및 응답 생성
        Estimate savedEstimate = estimateRepository.save(estimate);

        return EstimateResponse.from(savedEstimate);
    }
}
