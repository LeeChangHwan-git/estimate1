package com.storelabs.mygage.estimate.repository;

import com.storelabs.mygage.estimate.entity.ExpertEstimateDailyCount;
import com.storelabs.mygage.estimate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ExpertEstimateDailyCountRepository extends JpaRepository<ExpertEstimateDailyCount, Long> {
    Optional<ExpertEstimateDailyCount> findByExpertAndUseDate(User expert, LocalDate useDate);
}