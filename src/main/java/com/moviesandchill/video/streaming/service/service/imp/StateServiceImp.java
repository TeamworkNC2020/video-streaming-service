package com.moviesandchill.video.streaming.service.service.imp;

import com.moviesandchill.video.streaming.service.domain.State;
import com.moviesandchill.video.streaming.service.dto.StateDto;
import com.moviesandchill.video.streaming.service.mapper.StateMapper;
import com.moviesandchill.video.streaming.service.repository.StateRepository;
import com.moviesandchill.video.streaming.service.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StateServiceImp implements StateService {

    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private StateMapper stateMapper;
    @Override

    public List<StateDto> getAllState() {
        List<State> states = stateRepository.findAll();
        return stateMapper.listStateToListDto(states);
    }

    @Override
    public void deleteAllState() {
        stateRepository.deleteAll();
    }

    @Override
    public StateDto getStateById(Long stateID) {
        Optional<State> state = stateRepository.findById(stateID);
        return state.map(stateMapper::stateToDto).orElse(null);
    }

    @Override
    public StateDto addState(StateDto stateDto) {
        State state = stateMapper.dtoToState(stateDto);
        state = stateRepository.save(state);
        return stateMapper.stateToDto(state);
    }

    @Override
    public void deleteStateById(Long stateID) {
        stateRepository.deleteById(stateID);
    }
}
