package com.storelabs.mygage.estimate.entity;

import jakarta.persistence.*;
import lombok.*;

// 임시로 놓은 엔티티
@Entity
@Getter
public class ExpertInfo extends BaseTimeEntity {
    @Id
    private String expertId;

    @OneToOne
    @JoinColumn(name = "expert_id", referencedColumnName = "userId")
    private User user;
}
