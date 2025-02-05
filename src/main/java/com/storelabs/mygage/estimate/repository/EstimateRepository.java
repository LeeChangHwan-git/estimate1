package com.storelabs.mygage.estimate.repository;

import com.storelabs.mygage.estimate.entity.Estimate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstimateRepository extends JpaRepository<Estimate, Long> {

}
