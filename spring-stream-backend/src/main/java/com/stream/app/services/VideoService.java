package com.stream.app.services;

import com.stream.app.entities.Video;
import com.stream.app.util.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
public interface VideoService {

    Logger logger = LogManager.getLogger(VideoService.class);

    //save video
    Video save(Video video, MultipartFile file);


    //get video by id
    Video getById(String videoId);

    Video get(String videoId);

    //get video by title
    Video getByTitle(String title);

    List<Video> getAll();


}
