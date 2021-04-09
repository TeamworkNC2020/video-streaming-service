package com.moviesandchill.video.streaming.service.controller;

import com.amazonaws.services.s3.model.S3Object;
import com.moviesandchill.video.streaming.service.service.imp.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    VideoService videoService;

    private final String bucketName = "moviescontainer";

    @RequestMapping(value="/{bucketName}/{nameVideo}", method = RequestMethod.POST)
    public S3Object getMovieVideo(@PathVariable String bucketName,@PathVariable String nameVideo) {
        return videoService.getMovieVideo(bucketName,nameVideo);
    }


}
