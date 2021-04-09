package com.moviesandchill.video.streaming.service.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "state")
@Data
@NoArgsConstructor
public class State {

    @Id
    @Column(name = "stateID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stateID;

    @Column(name = "stateTitle")
    private String stateTitle;

    @OneToMany(mappedBy = "state")
    private List<Session> sessions = new ArrayList<>();
}
