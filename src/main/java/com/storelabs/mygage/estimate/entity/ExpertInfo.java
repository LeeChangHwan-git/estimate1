package com.storelabs.mygage.estimate.entity;

import jakarta.persistence.*;
import lombok.*;

// 임시로 놓은 엔티티
@Entity
@Getter
public class ExpertInfo extends BaseTimeEntity {
    @Id
    @Column(name = "expert_no")
    private Long expertNo;

    @OneToOne
    @JoinColumn(name = "expert_no", referencedColumnName = "userNo")
    @MapsId
    private User user;
}
