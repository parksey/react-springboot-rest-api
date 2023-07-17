package com.example.gonggam.space.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class SharedSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spaceId;

    @NotNull
    @Column(length = 50)
    private String title;

    private String description;

    @Column(nullable = false)
    private String location;

    private int capacity;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endAt;


    public SharedSpace(Long spaceId, String title, String description, String location, int capacity, LocalDateTime startAt, LocalDateTime endAt) {
        this.spaceId = spaceId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.capacity = capacity;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public SharedSpace(String title, String description, String location, int capacity, LocalDateTime startAt, LocalDateTime endAt) {
        this(null, title, description, location, capacity, startAt, endAt);
    }

    public SharedSpace() {

    }
}
