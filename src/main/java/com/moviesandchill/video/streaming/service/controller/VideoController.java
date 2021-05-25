package com.moviesandchill.video.streaming.service.controller;

import com.moviesandchill.video.streaming.service.service.imp.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    VideoService videoService;


    @PostMapping(path = "/{userId}/logo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateUserLogo(@PathVariable long userId, @RequestPart("file") MultipartFile file) throws IOException {
        return videoService.updateUserLogo(userId, file);
    }

}
