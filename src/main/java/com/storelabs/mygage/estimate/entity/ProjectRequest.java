package com.storelabs.mygage.estimate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.storelabs.mygage.estimate.converter.CategoryEnumConverter;
import com.storelabs.mygage.estimate.enums.Category;
import com.storelabs.mygage.estimate.enums.ProjectRequestStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "project_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectRequest extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_no")
    private Project project;

    @Convert(converter = CategoryEnumConverter.class)
    private Category category;

    // 요청 상태를 관리하기 위한 enum 추가
    @Enumerated(EnumType.STRING)
    private ProjectRequestStatus projectRequestStatus;

    // 견적서 양방향 관계 추가
    @OneToMany(mappedBy = "projectRequest", cascade = CascadeType.ALL)
    private List<Estimate> estimates = new ArrayList<>();

    // 양방향 관계 설정을 위한 편의 메서드
    public void setProject(Project project) {
        this.project = project;
        if (!project.getProjectRequests().contains(this)) {
            project.getProjectRequests().add(this);
        }
    }

    public void addEstimate(Estimate estimate) {
        this.estimates.add(estimate);
        estimate.setProjectRequest(this);
    }
}