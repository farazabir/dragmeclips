package com.dragme.faraz.dragmeclips.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/videos")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(
            @RequestParam("file") MultipartFile file,
            @RequestParam("email") String email,  @RequestParam("userId") String userId
    ){

        Video video = videoService.uploadVideo(file,email,userId);
        return ResponseEntity.ok("Video uploaded and processing started. Video ID: " + video.getId());
    }
}
