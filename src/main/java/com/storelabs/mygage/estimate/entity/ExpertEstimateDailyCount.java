package com.storelabs.mygage.estimate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "expert_estimate_daily_count",
        indexes = @Index(name = "idx_expert_date",
                columnList = "expert_no, expertNo"))
@Getter
@NoArgsConstructor
@ToString
public class ExpertEstimateDailyCount extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expert_no")
    private User expert;

    private LocalDate useDate;
    private Integer maxCount;
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer currentCount;

    @Version
    @Column(columnDefinition = "bigint default 0")
    private Long version;

    public boolean canCreateEstimate() {
        return currentCount < maxCount;
    }

    public void incrementCount() {
        this.currentCount++;
    }

    public ExpertEstimateDailyCount(User expert, LocalDate useDate, Integer maxCount) {
        this.expert = expert;
        this.useDate = useDate;
        this.maxCount = maxCount;
        this.currentCount = 0;  // 초기값 설정
    }
}
