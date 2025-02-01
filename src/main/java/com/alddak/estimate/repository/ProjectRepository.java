package com.alddak.estimate.repository;

import com.alddak.estimate.entity.Project;
import com.alddak.estimate.entity.User;
import com.alddak.estimate.enums.Category;
import com.alddak.estimate.enums.ProjectStatus;
import com.alddak.estimate.enums.ProjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Repository
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    boolean existsByClientAndProjectTypeAndCategoryAndStatus(
            User user,
            ProjectType projectType,
            Category category,
            ProjectStatus status
    );

    List<Project> findByStatusAndCategoryIn(ProjectStatus projectStatus, List<Category> expertCategories);
}

