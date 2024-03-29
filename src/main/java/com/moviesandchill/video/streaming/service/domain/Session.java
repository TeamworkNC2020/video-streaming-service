package com.moviesandchill.video.streaming.service.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "session")
@Data
@NoArgsConstructor
public class Session {

    @Id
    @Column(name = "sessionID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionID;

    @Column(name = "filmID")
    private Long filmID;

    @Column(name = "chatID")
    private Long chatID;

    @Column(name = "organizerID")
    private Long organizerID;

    @Column(name = "stopTime")
    private LocalTime stopTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fkState")
    private State state;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "session_has_watcher", joinColumns = @JoinColumn(name = "sessionID", referencedColumnName = "sessionID"),
            inverseJoinColumns = @JoinColumn(name = "watcherID", referencedColumnName = "watcherID"))
    private List<Watcher> watchers = new ArrayList<>();
}
