package com.moviesandchill.video.streaming.service.mapper;

import com.moviesandchill.video.streaming.service.domain.Session;
import com.moviesandchill.video.streaming.service.dto.SessionDto;
import com.moviesandchill.video.streaming.service.dto.SessionParDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface SessionMapper {

    Session dtoToSession(SessionDto sessionDto);

    Session dtoToSession(SessionParDto sessionParDto);

    SessionDto sessionToDto(Session session);

    SessionParDto sessionToParDto(Session session);

    List<Session> listDtoToListSession(List<SessionDto> sessionDtoList);

    List<SessionDto> listSessionToListDto(List<Session> sessionList);
}
