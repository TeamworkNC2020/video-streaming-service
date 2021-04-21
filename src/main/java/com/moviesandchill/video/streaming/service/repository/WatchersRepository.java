package com.moviesandchill.video.streaming.service.repository;

import com.moviesandchill.video.streaming.service.domain.Watcher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchersRepository extends JpaRepository<Watcher, Long> {
}
