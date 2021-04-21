package com.moviesandchill.video.streaming.service.dto;

import com.moviesandchill.video.streaming.service.domain.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessionDto {

    private Long sessionID;

    private Long filmID;

    private Long organizerID;

    private LocalTime stopTime;

    private State state;

    public SessionDto(SessionParDto sessionParDto,State state) {
        this.sessionID = sessionParDto.getSessionID();
        this.filmID = sessionParDto.getFilmID();
        this.organizerID = sessionParDto.getOrganizerID();
        this.stopTime = sessionParDto.getStopTime();
        this.state = state;
    }
}
