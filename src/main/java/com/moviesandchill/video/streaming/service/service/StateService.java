package com.moviesandchill.video.streaming.service.service;


import com.moviesandchill.video.streaming.service.dto.StateDto;

import java.util.List;

public interface StateService {

    List<StateDto> getAllState();

    void deleteAllState();

    StateDto getStateById(Long stateID);

    StateDto addState(StateDto stateDto);

    void deleteStateById(Long stateID);
}
