package com.storelabs.mygage.estimate.entity;

import com.storelabs.mygage.estimate.converter.CategoryEnumConverter;
import com.storelabs.mygage.estimate.enums.Category;
import com.storelabs.mygage.estimate.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Convert(converter = CategoryEnumConverter.class)
    private Category category;

    // 요청 상태를 관리하기 위한 enum 추가
    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    // 양방향 관계 설정을 위한 편의 메서드
    public void setProject(Project project) {
        this.project = project;
        if (!project.getProjectRequests().contains(this)) {
            project.getProjectRequests().add(this);
        }
    }

    public void setUser(User user) {
        this.user = user;
        if (!user.getProjectRequests().contains(this)) {
            user.getProjectRequests().add(this);
        }
    }
}