package com.example.gonggam.space.service;

import com.example.gonggam.space.domain.SharedSpace;
import com.example.gonggam.space.dto.SpaceCreateRequest;
import com.example.gonggam.space.dto.SpaceInfoResponse;
import com.example.gonggam.space.dto.SpaceSummary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpaceMapper {

    public SharedSpace toEntity(SpaceCreateRequest creationRequest) {
        return SharedSpace.builder()
                .title(creationRequest.getTitle())
                .description(creationRequest.getDescription())
                .location(creationRequest.getLocation())
                .amount(creationRequest.getAmount())
                .capacity(creationRequest.getCapacity())
                .startAt(creationRequest.getStartAt())
                .endAt(creationRequest.getEndAt())
                .build();
    }

    public SpaceInfoResponse toResponse(SharedSpace sharedSpace) {
        return SpaceInfoResponse.builder()
                .title(sharedSpace.getTitle())
                .capacity(sharedSpace.getCapacity())
                .description(sharedSpace.getDescription())
                .location(sharedSpace.getLocation())
                .amount(sharedSpace.getAmount())
                .startAt(sharedSpace.getStartAt())
                .endAt(sharedSpace.getEndAt())
                .build();
    }

    public List<SpaceSummary> toResponse(List<SharedSpace> sharedSpaces) {
        return sharedSpaces.stream()
                .map(sharedSpace -> toSpaceSummary(sharedSpace))
                .toList();
    }

    private SpaceSummary toSpaceSummary(SharedSpace sharedSpace) {
        return SpaceSummary.builder()
                .spaceId(sharedSpace.getSpaceId())
                .title(sharedSpace.getTitle())
                .amount(sharedSpace.getAmount())
                .capacity(sharedSpace.getCapacity())
                .build();
    }
}
