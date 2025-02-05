package com.storelabs.mygage.estimate.entity;

import com.storelabs.mygage.estimate.converter.EstimateStatusEnumConverter;
import com.storelabs.mygage.estimate.enums.EstimateStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estimates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder  // 빌더 패턴 추가
public class Estimate extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long estimateNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_request_id")
    private ProjectRequest projectRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expert_id")
    private ExpertInfo expertInfo;

    private String estimateAmt;
    private String detailedDescription;

    @Convert(converter = EstimateStatusEnumConverter.class)
    private EstimateStatus estimateStatus;

    @OneToMany(mappedBy = "estimate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FileEntity> files = new ArrayList<>();

    // 파일 추가 메서드
    public void addFile(FileEntity file) {
        this.files.add(file);
        file.setEstimate(this);
    }

    // 파일 제거 메서드
    public void removeFile(FileEntity file) {
        this.files.remove(file);
        file.setEstimate(null);
    }

    // Builder 패턴을 사용할 때 files 필드를 초기화
    public static class EstimateBuilder {
        private List<FileEntity> files = new ArrayList<>();

        public EstimateBuilder files(List<FileEntity> files) {
            this.files = files != null ? files : new ArrayList<>();
            return this;
        }
    }
}
