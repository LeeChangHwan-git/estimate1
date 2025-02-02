package com.alddak.estimate.controller;

import com.alddak.estimate.dto.response.FileUploadResponse;
import com.alddak.estimate.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping
    public ResponseEntity<FileUploadResponse> uploadFiles(
            @RequestParam("files") List<MultipartFile> files) {
        List<Long> fileIds = fileService.uploadFiles(files);
        return ResponseEntity.ok(new FileUploadResponse(fileIds));
    }
}
