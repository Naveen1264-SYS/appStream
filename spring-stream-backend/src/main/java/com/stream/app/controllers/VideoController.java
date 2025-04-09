package com.stream.app.controllers;

import com.stream.app.entities.Video;
import com.stream.app.payload.CustomMessage;
import com.stream.app.services.VideoService;
import com.stream.app.util.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/videos")
public class VideoController {
    Logger logger = LogManager.getLogger(VideoController.class);

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    // POST method to create a video
    @PostMapping
    public ResponseEntity<?> create(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description) {

        logger.info("CreateController: request-toCreate Video request {}", Mapper.mapToJsonString(file));

        // Create a new Video entity and set its properties
        Video video = new Video();
        video.setTitle(title);
        video.setDescription(description);
        video.setVideoId(UUID.randomUUID().toString());  // Generate a unique ID for the video

        // Log the response from the service
        logger.info("VideoController: create response from service {}", Mapper.mapToJsonString(video));

        // Save the video using the service
        Video savedVideo = videoService.save(video, file);

        // Check if the video was saved successfully
        if (savedVideo != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(savedVideo); // Return the saved video
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CustomMessage.builder()
                            .message("Video not uploaded")
                            .success(false)
                            .build()); // Return failure response if the video upload fails
        }
    }

    // GET method to retrieve a video by ID
    @GetMapping("/id/{videoId}")
    public ResponseEntity<?> getById(@PathVariable String videoId) {
        logger.info("VideoController: Request to get video by ID {}", videoId);

        // Fetch the video by ID from the service
        Video video = videoService.getById(videoId);

        // Check if the video was found
        if (video != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(video); // Return the found video
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(CustomMessage.builder()
                            .message("Video with the given ID not found")
                            .success(false)
                            .build()); // Return "not found" message if the video ID doesn't exist
        }
    }

    // GET method to retrieve a video by title
    @GetMapping("/title/{title}")
    public ResponseEntity<?> getByTitle(@PathVariable String title) {
        logger.info("VideoController: Request to get video by title {}", title);

        // Fetch the video by title from the service
        Video video = videoService.getByTitle(title);

        // Check if the video was found
        if (video != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(video); // Return the found video
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(CustomMessage.builder()
                            .message("Video with the given title not found")
                            .success(false)
                            .build()); // Return "not found" message if the video title doesn't exist
        }
    }
}
