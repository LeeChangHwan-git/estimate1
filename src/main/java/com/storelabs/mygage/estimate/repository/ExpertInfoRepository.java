package com.storelabs.mygage.estimate.repository;

import com.storelabs.mygage.estimate.entity.ExpertInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpertInfoRepository extends JpaRepository<ExpertInfo, String> {
    ExpertInfo findByUser_UserId(String userId);

}
