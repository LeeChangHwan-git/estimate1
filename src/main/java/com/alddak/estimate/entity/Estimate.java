package com.alddak.estimate.entity;

import com.alddak.estimate.converter.CategoryEnumConverter;
import com.alddak.estimate.converter.EstimateStatusEnumConverter;
import com.alddak.estimate.enums.EstimateStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Estimate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long estimateNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_no")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expert_no")
    private ExpertInfo expertInfo;

    private String estimateAmt;
    private String detailedDescription;

    @OneToMany(mappedBy = "estimate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FileEntity> files = new ArrayList<>();

    @Convert(converter = EstimateStatusEnumConverter.class)
    private EstimateStatus estimateStatus;

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
}
