package com.storelabs.mygage.estimate.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

@Getter
public abstract class BaseEntity extends BaseTimeEntity{
    @CreatedBy
    protected String createdBy;

    @LastModifiedBy
    protected String lastModifiedBy;
}

