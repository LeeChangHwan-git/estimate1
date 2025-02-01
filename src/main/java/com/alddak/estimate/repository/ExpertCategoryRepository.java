package com.alddak.estimate.repository;

import com.alddak.estimate.entity.ExpertCategory;
import com.alddak.estimate.entity.ExpertCategoryId;
import com.alddak.estimate.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpertCategoryRepository extends JpaRepository<ExpertCategory, ExpertCategoryId> {
    @Query("SELECT ec.id.category FROM ExpertCategory ec WHERE ec.id.userNo = :userNo")
    List<Category> findCategoriesByUserNo(@Param("userNo") Long userNo);
}
