package com.moviesandchill.video.streaming.service.service.imp;

import com.moviesandchill.video.streaming.service.domain.Watcher;
import com.moviesandchill.video.streaming.service.dto.WatcherDto;
import com.moviesandchill.video.streaming.service.mapper.WatcherMapper;
import com.moviesandchill.video.streaming.service.repository.WatchersRepository;
import com.moviesandchill.video.streaming.service.service.WatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WatcherServiceImp implements WatcherService {

    @Autowired
    WatchersRepository watchersRepository;
    @Autowired
    WatcherMapper watcherMapper;

    @Override
    public List<WatcherDto> getAllWatcher() {
        List<Watcher> watcherList = watchersRepository.findAll();
        return watcherMapper.listWatcherToListDto(watcherList);
    }

    @Override
    public void deleteAllWatcher() {
        watchersRepository.deleteAll();
    }

    @Override
    public WatcherDto getWatcherById(Long watcherID) {
        Optional<Watcher> watcher = watchersRepository.findById(watcherID);
        return watcher.map(watcherMapper::watcherToDto).orElse(null);
    }

    @Override
    public WatcherDto addWatcher(WatcherDto watcherDto) {
        Watcher watcher = watcherMapper.dtoToWatcher(watcherDto);
        watcher = watchersRepository.save(watcher);
        return watcherMapper.watcherToDto(watcher);
    }

    @Override
    public void deleteWatcherById(Long watcherID) {
        watchersRepository.deleteById(watcherID);
    }
}
