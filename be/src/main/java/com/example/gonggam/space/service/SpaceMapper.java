package com.example.gonggam.space.service;

import com.example.gonggam.space.domain.SharedSpace;
import com.example.gonggam.space.dto.SpaceCreateRequest;
import com.example.gonggam.space.dto.SpaceCreateResponse;
import org.springframework.stereotype.Component;

@Component
public class SpaceMapper {

    public SharedSpace toEntity(SpaceCreateRequest creationRequest) {
        return SharedSpace.builder()
                .title(creationRequest.getTitle())
                .email(creationRequest.getEmail())
                .description(creationRequest.getDescription())
                .location(creationRequest.getLocation())
                .capacity(creationRequest.getCapacity())
                .startAt(creationRequest.getStartAt())
                .endAt(creationRequest.getEndAt())
                .build();
    }

    public SpaceCreateResponse toResponse(SharedSpace sharedSpace) {
        return null;
    }
}
