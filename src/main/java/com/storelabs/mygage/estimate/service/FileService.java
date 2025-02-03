package com.storelabs.mygage.estimate.service;

import com.storelabs.mygage.estimate.entity.FileEntity;
import com.storelabs.mygage.estimate.enums.ErrorCode;
import com.storelabs.mygage.estimate.exception.BusinessException;
import com.storelabs.mygage.estimate.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    @Value("${file.upload.directory}")
    private String uploadDirectory;

    @Transactional
    public List<Long> uploadFiles(List<MultipartFile> multipartFiles) {
        List<Long> fileIds = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            try {
                FileEntity fileEntity = saveFile(multipartFile);
                fileRepository.save(fileEntity);
                fileIds.add(fileEntity.getId());
            } catch (IOException e) {
                throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR,
                        "Failed to save file: " + e.getMessage());
            }
        }
        return fileIds;
    }

    @Transactional(readOnly = true)
    public List<FileEntity> getFilesByIds(List<Long> fileIds) {
        return fileRepository.findAllById(fileIds);
    }

    private FileEntity saveFile(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        String storedFileName = UUID.randomUUID().toString() + getFileExtension(originalFilename);

        Path directory = Paths.get(uploadDirectory);
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }

        Path filePath = directory.resolve(storedFileName);
        Files.copy(multipartFile.getInputStream(), filePath);

        FileEntity file = new FileEntity();
        file.setOriginalFileName(originalFilename);
        file.setStoredFileName(storedFileName);
        file.setFilePath(filePath.toString());
        file.setContentType(multipartFile.getContentType());
        file.setFileSize(multipartFile.getSize());

        return file;
    }

    private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }
}