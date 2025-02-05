package com.storelabs.mygage.estimate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "files")
@Getter
@Setter
@NoArgsConstructor
public class FileEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalFileName;    // 원본 파일명
    private String storedFileName;      // 저장된 파일명
    private String filePath;            // 파일 저장 경로
    private String contentType;         // 파일 타입
    private Long fileSize;              // 파일 크기

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estimate_no")
    private Estimate estimate;
}
