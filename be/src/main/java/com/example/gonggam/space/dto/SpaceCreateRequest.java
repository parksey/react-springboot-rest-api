package com.example.gonggam.space.dto;

import com.example.gonggam.util.UtilsCode;
import com.example.gonggam.util.exception.ValidationStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SpaceCreateRequest {

    @NotBlank(message = ValidationStatus.Global.NOT_BLANK)
    private final String title;

    @NotBlank(message = ValidationStatus.Global.NOT_BLANK)
    private final String location;

    private final String description;

    @Min(value = UtilsCode.Space.MIN_SPACE_CAPACITY, message = ValidationStatus.Space.UNDER_MIN_CAPACIRY)
    private final int capacity;

    @Min(value = UtilsCode.Space.MIN_MONEY, message = ValidationStatus.Space.UNDER_MIN_CAPACIRY)
    private final long amount;

    @NotNull(message = ValidationStatus.Global.NO_DATA)
    private final LocalDateTime startAt;

    @NotNull(message = ValidationStatus.Global.NO_DATA)
    private final LocalDateTime endAt;

    @Builder
    public SpaceCreateRequest(final String title,
                              final String location,
                              final String description,
                              final int capacity,
                              final long amount,
                              final LocalDateTime startAt,
                              final LocalDateTime endAt) {
        this.title = title;
        this.location = location;
        this.description = description;
        this.capacity = capacity;
        this.amount = amount;
        this.startAt = startAt;
        this.endAt = endAt;
    }
}
