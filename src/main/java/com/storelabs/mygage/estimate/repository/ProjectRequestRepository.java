package com.storelabs.mygage.estimate.repository;

import com.storelabs.mygage.estimate.entity.ProjectRequest;
import com.storelabs.mygage.estimate.enums.Category;
import com.storelabs.mygage.estimate.enums.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRequestRepository extends JpaRepository<ProjectRequest, Long> {
    /**
     * 조건을 만족하는 ProjectRequest 목록을 조회한다
     * 1. 연관된 Project의 상태가 IN_PROGRESS
     * 2. ProjectRequest의 카테고리가 주어진 카테고리 목록에 포함됨
     */
    @Query("SELECT pr FROM ProjectRequest pr " +
            "JOIN FETCH pr.project p " +
            "LEFT JOIN pr.estimates e " +
            "WHERE p.status = :status " +
            "AND pr.category IN :categories " +
            "AND e IS NULL")
    List<ProjectRequest> findByProjectStatusAndCategoryInWithoutEstimates(
            @Param("status") ProjectStatus status,
            @Param("categories") List<Category> categories
    );
}
