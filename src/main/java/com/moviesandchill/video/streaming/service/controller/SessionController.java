package com.moviesandchill.video.streaming.service.controller;

import com.moviesandchill.video.streaming.service.dto.SessionDto;
import com.moviesandchill.video.streaming.service.service.SessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "/sessions",
        produces = "application/json"
)
public class SessionController {

    private SessionService sessionService;

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
    public SessionDto getSessionById(@PathVariable Long sessionID) {
        return sessionService.getSessionById(sessionID);
    }

    @PostMapping()
    public SessionDto addSession(@RequestBody SessionDto sessionDto) {
        return sessionService.addSession(sessionDto);
    }

    @DeleteMapping("/{sessionID}")
    public void deleteSessionById(@PathVariable Long sessionID) {
        sessionService.deleteSessionById(sessionID);
    }
}
