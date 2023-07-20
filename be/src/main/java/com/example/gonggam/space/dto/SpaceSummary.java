package com.example.gonggam.space.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SpaceSummary {

    private String title;
    private int capacity;
    private long amount;

    @Builder
    public SpaceSummary(final String title, final int capacity, final long amount) {
        this.title = title;
        this.capacity = capacity;
        this.amount = amount;
    }
}