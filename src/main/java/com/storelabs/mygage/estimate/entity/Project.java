package com.storelabs.mygage.estimate.entity;

import com.storelabs.mygage.estimate.converter.CategoryListConverter;
import com.storelabs.mygage.estimate.converter.ProjectTypeEnumConverter;
import com.storelabs.mygage.estimate.enums.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @JoinColumn(name = "user_no")
    private User user;

    // enum 값 추가가 많을 것으로 보여서 Converter를 만듬
    @Convert(converter = ProjectTypeEnumConverter.class)
    private ProjectType projectType;
    // @TODO 기타프로젝트에 맵핑되는 카테고리전문가를 어떻게 식별할 것인가
    private String customProjectType;

    @Convert(converter = CategoryListConverter.class)
    @Column(name = "categories", columnDefinition = "TEXT")
    @Builder.Default
    private List<Category> categories = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    // projectStatus의 상세내용을 관리하는 변수
    @Enumerated(EnumType.STRING)
    private ProjectStatusDetail projectStatusDetail;

    private LocalDate desiredDate;
    private String city;
    private String district;
    // 동이 영어로 뭐지...
    private String dong;

    private String squareFootage;
    private String budget;

    @Enumerated(EnumType.STRING)
    private StartupType startupType;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<ProjectRequest> projectRequests = new HashSet<>();

    // Getter에 null 체크 추가
    public List<Category> getCategories() {
        return categories != null ? categories : new ArrayList<>();
    }

    public void addProjectRequest(ProjectRequest request) {
        if (request != null && !this.projectRequests.contains(request)) {
            this.projectRequests.add(request);
            request.setProject(this);
        }
    }
}