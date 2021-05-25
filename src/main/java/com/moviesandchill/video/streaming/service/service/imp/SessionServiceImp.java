package com.moviesandchill.video.streaming.service.service.imp;

import com.moviesandchill.video.streaming.service.domain.Session;
import com.moviesandchill.video.streaming.service.domain.State;
import com.moviesandchill.video.streaming.service.domain.Watcher;
import com.moviesandchill.video.streaming.service.dto.SessionDto;
import com.moviesandchill.video.streaming.service.dto.SessionParDto;
import com.moviesandchill.video.streaming.service.dto.WatcherDto;
import com.moviesandchill.video.streaming.service.mapper.SessionMapper;
import com.moviesandchill.video.streaming.service.mapper.WatcherMapper;
import com.moviesandchill.video.streaming.service.repository.SessionRepository;
import com.moviesandchill.video.streaming.service.repository.StateRepository;
import com.moviesandchill.video.streaming.service.repository.WatchersRepository;
import com.moviesandchill.video.streaming.service.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SessionServiceImp implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    WatchersRepository watchersRepository;
    @Autowired
    WatcherMapper watcherMapper;
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
    public SessionParDto getSessionById(Long sessionID) {
        Optional<Session> session = sessionRepository.findById(sessionID);
        if(session.isPresent()){
            SessionParDto sessionParDto = sessionMapper.sessionToParDto(session.get());
            if(session.get().getState() != null){
                sessionParDto.setStateID(session.get().getState().getStateID());
            }
            return sessionParDto;
        }
        return null;
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

    @Override
    public void setSessionTimeAndState(Long sessionID, LocalTime newTime, Long stateID) throws Exception {
        Session session = sessionRepository.findById(sessionID).orElseThrow();
        State state = stateRepository.findById(stateID).orElseThrow();
        session.setStopTime(newTime);
        session.setState(state);
        sessionRepository.save(session);
    }

    @Override
    public SessionDto addSessionByParameters(SessionParDto sessionParDto) {
        Optional<State> state = stateRepository.findById(sessionParDto.getStateID());
        Session session = sessionMapper.dtoToSession(sessionParDto);
        session.setState(state.get());
        session = sessionRepository.save(session);
        return sessionMapper.sessionToDto(session);
    }

    @Override
    public List<WatcherDto> getAllWatcherWithSession(Long sessionID) {
        Optional<Session> session = sessionRepository.findById(sessionID);
        if(session.isPresent()){
            List<Watcher> watcherList = new ArrayList<>(session.get().getWatchers());
            return watcherMapper.listWatcherToListDto(watcherList);
        }
        return null;
    }

    @Override
    public void addWatcherToSession(Long watcherID, Long sessionID) throws Exception {
        Session session = sessionRepository.findById(sessionID).orElseThrow(() -> new Exception());
        Watcher watcher = watchersRepository.findById(watcherID).orElseThrow(() -> new Exception());
        session.getWatchers().add(watcher);
        sessionRepository.save(session);
    }
}
