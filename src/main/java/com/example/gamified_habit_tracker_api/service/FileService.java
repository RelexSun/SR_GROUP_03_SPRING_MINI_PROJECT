package com.example.gamified_habit_tracker_api.service;

import com.example.gamified_habit_tracker_api.model.entities.FileMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileService {
    FileMetadata fileUpload(MultipartFile file);

    InputStream getFileByFileName(String fileName);
}
