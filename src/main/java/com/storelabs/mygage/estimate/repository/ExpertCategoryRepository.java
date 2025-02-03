package com.storelabs.mygage.estimate.repository;

import com.storelabs.mygage.estimate.entity.ExpertCategory;
import com.storelabs.mygage.estimate.entity.ExpertCategoryId;
import com.storelabs.mygage.estimate.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpertCategoryRepository extends JpaRepository<ExpertCategory, ExpertCategoryId> {
//    @Query("SELECT ec.id.category FROM ExpertCategory ec WHERE ec.id. = :userNo")
//    List<Category> findCategoriesByUserNo(@Param("userNo") Long userNo);
}
