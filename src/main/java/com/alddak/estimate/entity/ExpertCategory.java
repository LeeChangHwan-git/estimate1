package com.alddak.estimate.entity;

import com.alddak.estimate.converter.CategoryEnumConverter;
import com.alddak.estimate.enums.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "expert_categories")
@Getter
@NoArgsConstructor
public class ExpertCategory {
    @EmbeddedId
    private ExpertCategoryId id;
}