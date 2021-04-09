package com.moviesandchill.video.streaming.service.repository;

import com.moviesandchill.video.streaming.service.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {
}
