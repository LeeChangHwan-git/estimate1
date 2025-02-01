package com.alddak.estimate.entity;

import com.alddak.estimate.converter.CategoryEnumConverter;
import com.alddak.estimate.converter.ProjectTypeEnumConverter;
import com.alddak.estimate.enums.Category;
import com.alddak.estimate.enums.ProjectStatus;
import com.alddak.estimate.enums.ProjectType;
import com.alddak.estimate.enums.StartupType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

// Entity
@Entity
@Table(name = "projects")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectNo;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User client;

    // enum 값 추가가 많을 것으로 보여서 Converter를 만듬
    @Convert(converter = ProjectTypeEnumConverter.class)
    private ProjectType projectType;
    // @TODO 기타프로젝트에 맵핑되는 카테고리전문가를 어떻게 식별할 것인가
    private String customProjectType;

    @Convert(converter = CategoryEnumConverter.class)
    private Category category;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    private LocalDate desiredDate;
    private String city;
    private String district;
    private String address;
    private String squareFootage;
    private String budget;

    @Enumerated(EnumType.STRING)
    private StartupType startupType;
}