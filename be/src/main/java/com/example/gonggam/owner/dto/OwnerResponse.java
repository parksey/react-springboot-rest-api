package com.example.gonggam.owner.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OwnerResponse {

    private String ownerNo;
    private String phone;
    private String email;
    private LocalDateTime createAt;

    @Builder
    public OwnerResponse(final String ownerNo,
                         final String phone,
                         final String email,
                         final LocalDateTime createAt) {
        this.ownerNo = ownerNo;
        this.phone = phone;
        this.email = email;
        this.createAt = createAt;
    }
}
