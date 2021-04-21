package com.moviesandchill.video.streaming.service.service;


import com.moviesandchill.video.streaming.service.dto.WatcherDto;

import java.util.List;

public interface WatcherService {

    List<WatcherDto> getAllWatcher();

    void deleteAllWatcher();

    WatcherDto getWatcherById(Long watcherID);

    WatcherDto addWatcher(WatcherDto watcherDto);

    void deleteWatcherById(Long watcherID);
}
