package com.moviesandchill.video.streaming.service.mapper;

import com.moviesandchill.video.streaming.service.domain.State;
import com.moviesandchill.video.streaming.service.dto.StateDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface StateMapper {

    State dtoToState(StateDto stateDto);

    StateDto stateToDto(State state);

    List<State> listDtoToListState(List<StateDto> stateDtoList);

    List<StateDto> listStateToListDto(List<State> stateList);
}
