package com.example.gonggam.reservation.service;

import com.example.gonggam.reservation.domain.Reservation;
import com.example.gonggam.reservation.dto.ReservesResponse;
import com.example.gonggam.space.domain.SharedSpace;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservationMapper {

    public List<ReservesResponse> toResponse(List<Reservation> reservations) {
        return reservations.stream()
                .map(reservation -> toReserveResponse(reservation))
                .toList();
    }

    private ReservesResponse toReserveResponse(Reservation reservation) {
        SharedSpace sharedSpace = reservation.getSharedSpace();

        return ReservesResponse.builder()
                .reservationStatus(reservation.getReservationStatus().name())
                .spaceId(sharedSpace.getSpaceId())
                .createAt(reservation.getCreateAt())
                .title(sharedSpace.getTitle())
                .location(sharedSpace.getLocation())
                .amount(sharedSpace.getAmount())
                .build();
    }
}
