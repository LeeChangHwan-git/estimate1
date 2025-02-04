package com.storelabs.mygage.estimate.repository;

import com.storelabs.mygage.estimate.entity.Estimate;
import com.storelabs.mygage.estimate.entity.User;
import com.storelabs.mygage.estimate.enums.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
//public interface EstimateRepository extends JpaRepository<Estimate, Long> {
//    List<Estimate> findByProjectUserAndProjectStatusNot(User client, ProjectStatus status);
//
//}
