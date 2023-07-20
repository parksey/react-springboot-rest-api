package com.example.gonggam.owner.service;

import com.example.gonggam.owner.domain.Owner;
import com.example.gonggam.owner.dto.OwnerUpdateRequest;
import com.example.gonggam.owner.dto.OwnerResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OwnerMapper {

    public Owner toEntity(OwnerUpdateRequest ownerRequest) {
        return Owner.builder()
                .ownerNo(ownerRequest.getOwnerNo())
                .phone(ownerRequest.getPhone())
                .email(ownerRequest.getEmail())
                .createAt(LocalDateTime.now())
                .build();
    }

    public OwnerResponse toResponse(Owner owner) {
        return OwnerResponse.builder()
                .ownerNo(owner.getOwnerNo())
                .email(owner.getEmail())
                .phone(owner.getPhone())
                .createAt(owner.getCreateAt())
                .build();
    }
}
