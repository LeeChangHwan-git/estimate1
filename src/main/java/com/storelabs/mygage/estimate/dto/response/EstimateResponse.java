package com.storelabs.mygage.estimate.dto.response;

import com.storelabs.mygage.estimate.entity.Estimate;
import com.storelabs.mygage.estimate.entity.ExpertInfo;
import com.storelabs.mygage.estimate.entity.FileEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class EstimateResponse {
    private Long estimateNo;
    private String expertId;  // ExpertInfo 객체 대신 ID만 사용
    private String expertName; // 필요한 경우 전문가 이름도 추가
    private String amount;
    private String description;
    private List<String> fileNames;

    public static EstimateResponse from(Estimate estimate) {
        return new EstimateResponse(
                estimate.getEstimateNo(),
                estimate.getExpertInfo().getUser().getUserId(),  // ID만 가져오기
                estimate.getExpertInfo().getUser().getName(),    // 이름만 가져오기
                estimate.getEstimateAmt(),
                estimate.getDetailedDescription(),
                estimate.getFiles().stream()
                        .map(FileEntity::getOriginalFileName)
                        .collect(Collectors.toList())
        );
    }

}