package com.alddak.estimate.repository;

import com.alddak.estimate.entity.Estimate;
import com.alddak.estimate.entity.User;
import com.alddak.estimate.enums.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstimateRepository extends JpaRepository<Estimate, Long> {
    List<Estimate> findByProjectClientAndProjectStatusNot(User client, ProjectStatus status);

}
