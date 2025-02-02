package com.alddak.estimate.dto.response;

import com.alddak.estimate.entity.Estimate;
import com.alddak.estimate.entity.ExpertInfo;
import com.alddak.estimate.entity.FileEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class EstimateResponse {
    private Long estimateNo;
    private ExpertInfo expertInfo;  // ExpertInfo 객체 직접 사용
    private String amount;
    private String description;
    private List<String> fileNames;

    public static EstimateResponse from(Estimate estimate) {
        return new EstimateResponse(
                estimate.getEstimateNo(),
                estimate.getExpertInfo(),   // ExpertInfo 객체 그대로 전달
                estimate.getEstimateAmt(),
                estimate.getDetailedDescription(),
                estimate.getFiles().stream()
                        .map(FileEntity::getOriginalFileName)
                        .collect(Collectors.toList())
        );
    }

}