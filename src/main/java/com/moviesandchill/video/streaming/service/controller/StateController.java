package com.moviesandchill.video.streaming.service.controller;

import com.moviesandchill.video.streaming.service.dto.StateDto;
import com.moviesandchill.video.streaming.service.service.StateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "/states",
        produces = "application/json"
)
public class StateController {

    private StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @GetMapping
    public List<StateDto> getAllState() {
        return stateService.getAllState();
    }

    @DeleteMapping
    public void deleteAllState() {
        stateService.deleteAllState();
    }

    @GetMapping("/{stateID}")
    public StateDto getStateById(@PathVariable Long stateID) {
        return stateService.getStateById(stateID);
    }

    @PostMapping
    public StateDto addState(@RequestBody StateDto stateDto) {
        return stateService.addState(stateDto);
    }

    @DeleteMapping("/{stateID}")
    public void deleteStateById(@PathVariable Long stateID) {
        stateService.deleteStateById(stateID);
    }
}
