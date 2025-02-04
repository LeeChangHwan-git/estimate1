package com.storelabs.mygage.estimate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "expert_estimate_daily_count",
        indexes = @Index(name = "idx_expert_date",
                columnList = "expert_id, use_date"))
@Getter
@NoArgsConstructor
public class ExpertEstimateDailyCount extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expert_id")
    private User expert;

    private LocalDate useDate;
    private Integer maxCount;
    private Integer currentCount;
    @Version
    private Long version; // 동시성제어 - 현재 규모로는 전혀 필요없어보임

    public boolean canCreateEstimate() {
        return currentCount < maxCount;
    }

    public void incrementCount() {
        this.currentCount++;
    }
}
