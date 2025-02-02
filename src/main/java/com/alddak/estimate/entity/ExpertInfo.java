package com.alddak.estimate.entity;

import jakarta.persistence.*;
import lombok.*;

// 임시로 놓은 엔티티
@Entity
public class ExpertInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expertNo;
}
