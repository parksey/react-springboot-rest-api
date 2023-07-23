package com.example.gonggam.reservation.service;

import com.example.gonggam.customer.domain.Customer;
import com.example.gonggam.customer.exception.CustomerException;
import com.example.gonggam.customer.repository.CustomerRepository;
import com.example.gonggam.reservation.domain.Reservation;
import com.example.gonggam.reservation.dto.ReserveRequest;
import com.example.gonggam.reservation.dto.ReservesResponse;
import com.example.gonggam.reservation.exception.ReservationException;
import com.example.gonggam.reservation.repository.ReservationRepository;
import com.example.gonggam.space.domain.SharedSpace;
import com.example.gonggam.space.exception.SharedSpaceException;
import com.example.gonggam.space.repository.SharedSpaceRepository;
import com.example.gonggam.util.exception.CustomValidationStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final SharedSpaceRepository sharedSpaceRepository;
    private final ReservationMapper reservationMapper;

    public ReservationService(ReservationRepository reservationRepository, SharedSpaceRepository sharedSpaceRepository, CustomerRepository customerRepository, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.sharedSpaceRepository = sharedSpaceRepository;
        this.customerRepository = customerRepository;
        this.reservationMapper = reservationMapper;
    }

    @Transactional
    public void reserve(ReserveRequest request, String email) {
        SharedSpace sharedSpace = sharedSpaceRepository.findById(request.getSpaceId())
                .orElseThrow(()-> new SharedSpaceException(CustomValidationStatus.NO_SPACE));
        Customer customer =  customerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomerException(CustomValidationStatus.NO_USER));

        boolean alreadyReservation = reservationRepository.existsByCustomerIdAndSpaceId(customer.getCustomerId(), sharedSpace.getSpaceId());

        if (alreadyReservation) {
            throw new ReservationException(CustomValidationStatus.ALREADY_RESERVED);
        }

        Reservation reservation = Reservation.builder()
                .customerId(customer.getCustomerId())
                .sharedSpace(sharedSpace)
                .build();

        reservationRepository.save(reservation);
    }

    @Transactional
    public List<ReservesResponse> myReserves(String email) {
        Customer customer =  customerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomerException(CustomValidationStatus.NO_USER));

        List<Reservation> reservations = reservationRepository.findAllByCustomerId(customer.getCustomerId());

        return reservationMapper.toResponse(reservations);
    }
}
