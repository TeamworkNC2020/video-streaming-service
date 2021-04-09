package com.moviesandchill.video.streaming.service.service;

import com.moviesandchill.video.streaming.service.dto.SessionDto;

import java.util.List;

public interface SessionService {

    List<SessionDto> getAllSession();

    void deleteAllSession();

    SessionDto getSessionById(Long sessionID);

    SessionDto addSession(SessionDto sessionDto);

    void deleteSessionById(Long sessionID);
}
