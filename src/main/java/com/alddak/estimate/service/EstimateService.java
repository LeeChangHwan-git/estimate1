package com.alddak.estimate.service;

import com.alddak.estimate.dto.request.EstimateCreateRequest;
import com.alddak.estimate.dto.response.EstimateResponse;
import com.alddak.estimate.entity.Estimate;
import com.alddak.estimate.entity.FileEntity;
import com.alddak.estimate.entity.Project;
import com.alddak.estimate.entity.User;
import com.alddak.estimate.enums.ErrorCode;
import com.alddak.estimate.enums.EstimateStatus;
import com.alddak.estimate.enums.ProjectStatus;
import com.alddak.estimate.exception.BusinessException;
import com.alddak.estimate.repository.EstimateRepository;
import com.alddak.estimate.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstimateService {
    private final UserService userService;
    private final ProjectRepository projectRepository;
    private final FileService fileService;
    private final EstimateRepository estimateRepository;

    @Transactional
    public EstimateResponse createEstimate(EstimateCreateRequest request) {
        // 1. 사용자 검증
        User user = userService.findUserById(request.getUserId());

        // 2. 프로젝트 조회
        Project project = projectRepository.findById(request.getProjectNo())
                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_INPUT_VALUE,
                        "Project not found with ID: " + request.getProjectNo()));

        // 3. 견적 엔티티 생성
        Estimate estimate = new Estimate();
        estimate.setProject(project);
        estimate.setEstimateAmt(request.getAmount());
        estimate.setDetailedDescription(request.getDescription());
        estimate.setEstimateStatus(EstimateStatus.ESTIMATE_REQ);

        // @TODO 구현해야함
        // 4. ExpertInfo 설정
//        ExpertInfo expertInfo = expertInfoRepository.findById(request.getExpertNo())
//                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_INPUT_VALUE,
//                        "ExpertInfo not found with ID: " + request.getExpertNo()));
//        estimate.setExpertInfo(expertInfo);

        // 5. 파일 연결
        if (request.getFileIds() != null && !request.getFileIds().isEmpty()) {
            List<FileEntity> files = fileService.getFilesByIds(request.getFileIds());
            files.forEach(estimate::addFile);
        }

        // 6. 견적 저장 및 응답 생성
        Estimate savedEstimate = estimateRepository.save(estimate);
        return EstimateResponse.from(savedEstimate);
    }

    @Transactional(readOnly = true)
    public List<EstimateResponse> findEstimatesByUserId(String userId) {
        // 1. 사용자 조회
        User user = userService.findUserById(userId);

        // 2. 사용자의 진행중인 프로젝트들에 대한 견적 조회
        List<Estimate> estimates = estimateRepository.findByProjectClientAndProjectStatusNot(
                user,
                ProjectStatus.COMPLETED
        );

        // 3. 응답 변환
        return estimates.stream()
                .map(EstimateResponse::from)
                .collect(Collectors.toList());
    }
}
