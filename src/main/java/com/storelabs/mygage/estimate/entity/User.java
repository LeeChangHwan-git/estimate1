package com.storelabs.mygage.estimate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.storelabs.mygage.estimate.enums.UserType;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
public class User extends BaseTimeEntity {
    @Id
    private String userId;
    private String password;
    private String name;
    private String mobile;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Project> projects = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ProjectRequest> projectRequests = new ArrayList<>();

    // 양방향 관계 설정을 위한 편의 메서드
    public void addProject(Project project) {
        this.projects.add(project);
        if (project.getUser() != this) {
            project.setUser(this);
        }
    }

    public void addProjectRequest(ProjectRequest request) {
        this.projectRequests.add(request);
        if (request.getUser() != this) {
            request.setUser(this);
        }
    }
}
