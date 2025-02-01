package com.alddak.estimate.entity;

import com.alddak.estimate.converter.CategoryEnumConverter;
import com.alddak.estimate.enums.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class ExpertCategoryId implements Serializable {
    @Column(name = "user_no")
    private Long userNo;

    @Convert(converter = CategoryEnumConverter.class)
    @Column(name = "category", nullable = false)
    private Category category;
}