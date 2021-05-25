package com.moviesandchill.video.streaming.service.controller;

import com.moviesandchill.video.streaming.service.domain.Session;
import com.moviesandchill.video.streaming.service.domain.State;
import com.moviesandchill.video.streaming.service.dto.SessionDto;
import com.moviesandchill.video.streaming.service.dto.SessionParDto;
import com.moviesandchill.video.streaming.service.dto.WatcherDto;
import com.moviesandchill.video.streaming.service.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping(
        path = "/sessions",
        produces = "application/json"
)
public class SessionController {

    private SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public List<SessionDto> getAllSession() {
        return sessionService.getAllSession();
    }

    @DeleteMapping
    public void deleteAllSession() {
        sessionService.deleteAllSession();
    }

    @GetMapping("/{sessionID}")
    public SessionParDto getSessionById(@PathVariable Long sessionID) {
        return sessionService.getSessionById(sessionID);
    }

    @PostMapping("/{sessionID}/setTime")
    public SessionDto setSessionTime(@PathVariable Long sessionID,@RequestBody  LocalTime newTime) {
        return sessionService.setSessionTime(sessionID,newTime);
    }

    @PostMapping("/{sessionID}/setState/{stateID}")
    public SessionDto setSessionState(@PathVariable Long sessionID,@PathVariable  Long stateID) {
        return sessionService.setSessionState(sessionID,stateID);
    }

    @PostMapping()
    public SessionDto addSession(@RequestBody SessionDto sessionDto) {
        return sessionService.addSession(sessionDto);
    }

    @DeleteMapping("/{sessionID}")
    public void deleteSessionById(@PathVariable Long sessionID) {
        sessionService.deleteSessionById(sessionID);
    }

    @PostMapping("/param")
    public SessionDto addSessionByParameters(@RequestBody SessionParDto sessionParDto) {
        return sessionService.addSessionByParameters(sessionParDto);
    }

    @GetMapping("/{sessionID}/watchers")
    public List<WatcherDto> getAllWatcherWithSession(@PathVariable Long sessionID) {
        return sessionService.getAllWatcherWithSession(sessionID);
    }

    @PostMapping("/{sessionID}/addWatcher/{watcherID}")
    public void addWatcherToSession(@PathVariable Long sessionID,@PathVariable Long watcherID) throws Exception {
        sessionService.addWatcherToSession(watcherID,sessionID);
    }

}
