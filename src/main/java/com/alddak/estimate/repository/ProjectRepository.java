package com.alddak.estimate.repository;

import com.alddak.estimate.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}

