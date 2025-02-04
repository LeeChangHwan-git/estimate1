package com.storelabs.mygage.estimate.entity;

import com.storelabs.mygage.estimate.converter.CategoryEnumConverter;
import com.storelabs.mygage.estimate.converter.ProjectTypeEnumConverter;
import com.storelabs.mygage.estimate.enums.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Entity
@Entity
@Table(name = "projects")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectNo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // enum 값 추가가 많을 것으로 보여서 Converter를 만듬
    @Convert(converter = ProjectTypeEnumConverter.class)
    private ProjectType projectType;
    // @TODO 기타프로젝트에 맵핑되는 카테고리전문가를 어떻게 식별할 것인가
    private String customProjectType;

    @Convert(converter = CategoryEnumConverter.class)
    private Category category;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    // projectStatus의 상세내용을 관리하는 변수
    @Enumerated(EnumType.STRING)
    private ProjectStatusDetail projectStatusDetail;

    private LocalDate desiredDate;
    private String city;
    private String district;
    private String address;
    private String squareFootage;
    private String budget;

    @Enumerated(EnumType.STRING)
    private StartupType startupType;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectRequest> projectRequests = new ArrayList<>();

    // 양방향 관계 설정을 위한 편의 메서드
    public void addProjectRequest(ProjectRequest request) {
        this.projectRequests.add(request);
        if (request.getProject() != this) {
            request.setProject(this);
        }
    }
}