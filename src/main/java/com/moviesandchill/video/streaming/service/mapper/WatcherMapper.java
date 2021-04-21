package com.moviesandchill.video.streaming.service.mapper;

import com.moviesandchill.video.streaming.service.domain.Watcher;
import com.moviesandchill.video.streaming.service.dto.WatcherDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface WatcherMapper {

    Watcher dtoToWatcher(WatcherDto watcherDto);

    WatcherDto watcherToDto(Watcher watcher);

    List<Watcher> listDtoToListWatcher(List<WatcherDto> watcherDtoList);

    List<WatcherDto> listWatcherToListDto(List<Watcher> watcherList);
}
