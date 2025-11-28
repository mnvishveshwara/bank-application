package com.bankbroker.loanapp.service.storage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class FileStorageService {

    private final String UPLOAD_DIR = "uploads/";

    public String store(MultipartFile file) {
        try {
            String uniqueName = UUID.randomUUID() + "-" + file.getOriginalFilename();

            Path path = Paths.get(UPLOAD_DIR + uniqueName);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());

            return path.toString();
        } catch (IOException e) {
            throw new RuntimeException("File save failed", e);
        }
    }
}

