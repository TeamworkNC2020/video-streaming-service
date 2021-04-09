package com.moviesandchill.video.streaming.service.repository;

import com.moviesandchill.video.streaming.service.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
