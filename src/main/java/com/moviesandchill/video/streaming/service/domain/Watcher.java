package com.moviesandchill.video.streaming.service.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "watchers")
@Data
@NoArgsConstructor
public class Watcher {

    @Id
    @Column(name = "watcherID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long watcherID;

    @Column(name = "userID")
    private Long userID;

    @ManyToMany(mappedBy = "watchers")
    private List<Session> sessions;
}
