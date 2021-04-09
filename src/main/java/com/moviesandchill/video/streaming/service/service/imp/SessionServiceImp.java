package com.moviesandchill.video.streaming.service.service.imp;

import com.moviesandchill.video.streaming.service.domain.Session;
import com.moviesandchill.video.streaming.service.dto.SessionDto;
import com.moviesandchill.video.streaming.service.mapper.SessionMapper;
import com.moviesandchill.video.streaming.service.repository.SessionRepository;
import com.moviesandchill.video.streaming.service.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionServiceImp implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private SessionMapper sessionMapper;

    @Override
    public List<SessionDto> getAllSession() {
        List<Session> sessions = sessionRepository.findAll();
        return sessionMapper.listSessionToListDto(sessions);
    }

    @Override
    public void deleteAllSession() {
        sessionRepository.deleteAll();
    }

    @Override
    public SessionDto getSessionById(Long sessionID) {
        Optional<Session> session = sessionRepository.findById(sessionID);
        return session.map(sessionMapper::sessionToDto).orElse(null);
    }

    @Override
    public SessionDto addSession(SessionDto sessionDto) {
        Session session = sessionMapper.dtoToSession(sessionDto);
        session = sessionRepository.save(session);
        return sessionMapper.sessionToDto(session);
    }

    @Override
    public void deleteSessionById(Long sessionID) {
        sessionRepository.deleteById(sessionID);
    }

}
