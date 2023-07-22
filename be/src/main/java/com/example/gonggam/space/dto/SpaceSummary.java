package com.example.gonggam.space.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SpaceSummary {

    private Long spaceId;
    private String title;
    private int capacity;
    private long amount;

    @Builder
    public SpaceSummary(final Long spaceId,
                        final String title,
                        final int capacity,
                        final long amount) {
        this.spaceId = spaceId;
        this.title = title;
        this.capacity = capacity;
        this.amount = amount;
    }
}