package com.storelabs.mygage.estimate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FileUploadResponse {
    private List<Long> fileIds;
}
