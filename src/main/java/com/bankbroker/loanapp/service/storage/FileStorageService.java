package com.bankbroker.loanapp.service.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Slf4j
@Service
public class FileStorageService {

    private static final String BASE_DIR = "C:/app/uploads";

    public String store(
            MultipartFile file,
            String applicationId,
            String module,
            String fileName) {

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        try {
            Path dir = Paths.get(
                    BASE_DIR,
                    applicationId,
                    module
            );

            Files.createDirectories(dir);

            Path targetPath = dir.resolve(fileName);

            Files.copy(
                    file.getInputStream(),
                    targetPath,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return targetPath.toAbsolutePath().toString();

        } catch (IOException e) {
            throw new RuntimeException("File save failed", e);
        }
    }

    public String getExtension(String filename) {
        if (filename == null || !filename.contains(".")) return "";
        return filename.substring(filename.lastIndexOf("."));
    }

    public void delete(String filePath) {

        if (filePath == null || filePath.isBlank()) return;

        try {

            Path path = Paths.get(filePath);

            if (Files.exists(path)) {
                Files.delete(path);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to delete file: " + filePath, e);
        }
    }
}
