package com.example.gonggam.space.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SpaceCreateResponse {

    private String title;
    private String description;
    private String location;
    private int capacity;
    private long amount;
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    @Builder
    public SpaceCreateResponse(final String title,
                               final String description,
                               final String location,
                               final int capacity,
                               final long amount,
                               final LocalDateTime startAt,
                               final LocalDateTime endAt) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.capacity = capacity;
        this.amount = amount;
        this.startAt = startAt;
        this.endAt = endAt;
    }
}
