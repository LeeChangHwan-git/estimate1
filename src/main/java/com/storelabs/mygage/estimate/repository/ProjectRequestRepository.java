package com.storelabs.mygage.estimate.repository;

import com.storelabs.mygage.estimate.entity.ProjectRequest;
import com.storelabs.mygage.estimate.enums.Category;
import com.storelabs.mygage.estimate.enums.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRequestRepository extends JpaRepository<ProjectRequest, Long> {

    @Query("SELECT pr FROM ProjectRequest pr " +
            "JOIN FETCH pr.project p " +
            "LEFT JOIN pr.estimates e " +
            "WHERE p.status = :status " +
            "AND pr.category IN :categories " +
            "AND (e IS NULL OR (e.expertInfo.expertId <> :expertId OR e.estimateStatus <> 'ESTIMATE_COMPLETED'))")
    List<ProjectRequest> findByProjectStatusAndCategoryInWithoutEstimates(
            @Param("status") ProjectStatus status,
            @Param("categories") List<Category> categories,
            @Param("expertId") String expertId
    );
}
