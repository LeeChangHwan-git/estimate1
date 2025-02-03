package com.storelabs.mygage.estimate.entity;

import com.storelabs.mygage.estimate.converter.CategoryEnumConverter;
import com.storelabs.mygage.estimate.enums.Category;
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
    @Column(name = "user_id")
    private String userId;

    @Convert(converter = CategoryEnumConverter.class)
    @Column(name = "category", nullable = false)
    private Category category;
}