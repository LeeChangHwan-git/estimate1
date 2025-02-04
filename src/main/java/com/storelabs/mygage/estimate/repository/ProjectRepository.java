package com.storelabs.mygage.estimate.repository;

import com.storelabs.mygage.estimate.entity.Project;
import com.storelabs.mygage.estimate.entity.User;
import com.storelabs.mygage.estimate.enums.Category;
import com.storelabs.mygage.estimate.enums.ProjectStatus;
import com.storelabs.mygage.estimate.enums.ProjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Repository
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    boolean existsByUserAndProjectType(User user, ProjectType projectType);

    List<Project> findByStatusAndCategoryIn(ProjectStatus projectStatus, List<Category> expertCategories);

    List<Project> findByUserAndStatus(User user, ProjectStatus status);

    List<Project> findByUser_UserId(String userId);
}

