package com.example.gonggam.reservation.controller;

import com.example.gonggam.reservation.dto.ReserveRequest;
import com.example.gonggam.reservation.dto.ReservesResponse;
import com.example.gonggam.reservation.service.ReservationService;
import com.example.gonggam.space.exception.SharedSpaceException;
import com.example.gonggam.util.UtilsCode;
import com.example.gonggam.util.exception.CustomValidationStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reservation")
    public ResponseEntity<Void> reserve(@RequestBody @Valid ReserveRequest request, HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession(false);
        if (session == null) {
            throw new SharedSpaceException(CustomValidationStatus.LOGIN_ERROR);
        }

        String email = (String) session.getAttribute(UtilsCode.Global.EMAIL);
        reservationService.reserve(request, email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/myReservations")
    public ResponseEntity<List<ReservesResponse>> myRegist(HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession(false);
        if (session == null) {
            throw new SharedSpaceException(CustomValidationStatus.LOGIN_ERROR);
        }

        String email = (String) session.getAttribute(UtilsCode.Global.EMAIL);
        List<ReservesResponse> reservesResponses =reservationService.myReserves(email);
        return new ResponseEntity<>(reservesResponses, HttpStatus.OK);
    }
}
