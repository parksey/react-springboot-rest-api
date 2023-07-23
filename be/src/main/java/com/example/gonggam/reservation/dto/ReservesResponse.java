package com.example.gonggam.reservation.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReservesResponse {

    private final String reservationStatus;

    private final LocalDateTime createAt;

    private final String title;
    private final String location;
    private final long amount;
    private final long spaceId;

    @Builder
    public ReservesResponse(final String reservationStatus,
                            final LocalDateTime createAt,
                            final String title,
                            final String location,
                            final long amount,
                            final long spaceId) {
        this.reservationStatus = reservationStatus;
        this.createAt = createAt;
        this.title = title;
        this.location = location;
        this.amount = amount;
        this.spaceId = spaceId;
    }
}
