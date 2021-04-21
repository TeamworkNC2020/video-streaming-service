package com.moviesandchill.video.streaming.service.controller;

import com.moviesandchill.video.streaming.service.dto.WatcherDto;
import com.moviesandchill.video.streaming.service.service.WatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "/watchers",
        produces = "application/json"
)
public class WatcerController {

    @Autowired
    WatcherService watcherService;

    @GetMapping
    public List<WatcherDto> getAllWatcher() {
        return watcherService.getAllWatcher();
    }

    @DeleteMapping
    public void deleteAllWatcher() {
        watcherService.deleteAllWatcher();
    }

    @GetMapping("/{watcherID}")
    public WatcherDto getWatcherById(@PathVariable Long watcherID) {
        return watcherService.getWatcherById(watcherID);
    }

    @PostMapping
    public WatcherDto addWatcher(@RequestBody WatcherDto watcherDto) {
        return watcherService.addWatcher(watcherDto);
    }

    @DeleteMapping("/{watcherID}")
    public void deleteWatcherById(@PathVariable Long watcherID) {
        watcherService.deleteWatcherById(watcherID);
    }
}
