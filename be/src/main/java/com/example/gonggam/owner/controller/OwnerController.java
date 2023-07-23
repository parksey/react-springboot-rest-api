package com.example.gonggam.owner.controller;

import com.example.gonggam.owner.dto.OwnerLoginRequest;
import com.example.gonggam.owner.dto.OwnerRemoveRequest;
import com.example.gonggam.owner.dto.OwnerResponse;
import com.example.gonggam.owner.dto.OwnerUpdateRequest;
import com.example.gonggam.owner.service.OwnerService;
import com.example.gonggam.util.UtilsCode;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/{ownerNo}")
    public ResponseEntity<OwnerResponse> findOwnerInfo(@PathVariable String ownerNo) {
        OwnerResponse ownerResponse = ownerService.findOwnerInfo(ownerNo);
        return new ResponseEntity<>(ownerResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OwnerResponse> createOwner(@RequestBody @Valid OwnerUpdateRequest ownerRequest) {
        OwnerResponse ownerResponse = ownerService.createOperator(ownerRequest);
        return new ResponseEntity<>(ownerResponse, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteOwner(@RequestBody @Valid OwnerRemoveRequest ownerRequest) {
        ownerService.deleteOwner(ownerRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody OwnerLoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
        boolean canLogin = ownerService.login(loginRequest);

        if (canLogin) {
            HttpSession session = request.getSession();
            session.setAttribute(UtilsCode.Global.OWNER_NO, loginRequest.getOwnerNo());

            Cookie cookie = new Cookie("sessionId", session.getId());
            cookie.setHttpOnly(false);
            response.addCookie(cookie);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
