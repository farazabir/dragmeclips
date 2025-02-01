package com.dragme.faraz.dragmeclips.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;
import java.nio.file.InvalidPathException;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    private static final String UPLOAD_DIR = "uploads";

    public Video uploadVideo(MultipartFile file, String userId, String userEmail) {
        try {
            Path uploadDir = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            String videoId = UUID.randomUUID().toString();
            String originalFileName = sanitizeFileName(Objects.requireNonNull(file.getOriginalFilename()));
            String videoPath = UPLOAD_DIR + "/" + videoId + "_" + originalFileName;

            Files.copy(file.getInputStream(), Paths.get(videoPath));

            Video video = Video.builder()
                    .id(videoId)
                    .originalPath(videoPath)
                    .userId(userId)
                    .userEmail(userEmail)
                    .build();
            videoRepository.save(video);


            kafkaProducerService.sendVideoUploadEvent(videoId, videoPath);

            return video;
        } catch (IOException | InvalidPathException e) {

            throw new RuntimeException("Failed to upload video", e);
        }
    }

    private String sanitizeFileName(String originalFileName) {
        return originalFileName.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
    }
}
