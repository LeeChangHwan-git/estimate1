package com.storelabs.mygage.estimate.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Schema(description = "견적 생성 요청")
@Getter
@Setter
public class EstimateCreateRequest {
    @Schema(description = "사용자 ID", example = "user123")
    private String userId;

    @Schema(description = "프로젝트 번호", example = "1")
    private Long projectNo;

    @Schema(description = "견적 금액", example = "1000000")
    private String amount;

    @Schema(description = "상세 설명", example = "자세한 견적 설명입니다.")
    private String description;

    @Schema(description = "첨부 파일 ID 목록")
    private List<Long> fileIds;
}