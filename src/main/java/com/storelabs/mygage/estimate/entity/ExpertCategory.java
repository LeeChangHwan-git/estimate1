package com.storelabs.mygage.estimate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "expert_categories")
@Getter
@NoArgsConstructor
public class ExpertCategory extends BaseTimeEntity {
    @EmbeddedId
    private ExpertCategoryId id;
}