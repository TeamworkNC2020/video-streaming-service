package com.moviesandchill.video.streaming.service.service;

import com.moviesandchill.video.streaming.service.dto.SessionDto;
import com.moviesandchill.video.streaming.service.dto.SessionParDto;
import com.moviesandchill.video.streaming.service.dto.WatcherDto;

import java.time.LocalTime;
import java.util.List;

public interface SessionService {

    List<SessionDto> getAllSession();

    void deleteAllSession();

    SessionParDto getSessionById(Long sessionID);

    SessionDto addSession(SessionDto sessionDto);

    void deleteSessionById(Long sessionID);

    SessionDto setSessionTime(Long sessionID,LocalTime newTime);

    SessionDto setSessionState(Long sessionID,Long stateID);

    SessionDto addSessionByParameters(SessionParDto sessionParDto);

    List<WatcherDto> getAllWatcherWithSession(Long sessionID);

    void addWatcherToSession(Long watcherID,Long sessionID) throws Exception;
}
