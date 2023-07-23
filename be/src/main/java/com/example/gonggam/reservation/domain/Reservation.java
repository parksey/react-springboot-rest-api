package com.example.gonggam.reservation.domain;

import com.example.gonggam.space.domain.SharedSpace;
import com.example.gonggam.util.exception.ValidationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToOne
    @JoinColumn(name = "space_id")
    private SharedSpace sharedSpace;

    @NotNull(message = ValidationStatus.Global.NO_DATA)
    private Long customerId;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    LocalDateTime createAt;

    @Builder
    public Reservation(final SharedSpace sharedSpace,
                       final Long customerId) {
        this.sharedSpace = sharedSpace;
        this.customerId = customerId;
        this.reservationStatus = ReservationStatus.YET;
        this.createAt = LocalDateTime.now();
    }

    protected Reservation() {

    }
}
