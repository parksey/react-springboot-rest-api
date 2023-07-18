package com.example.gonggam.space.dto;

import com.example.gonggam.util.ErrorMessage;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SpaceCreateRequest {

    @NotBlank(message = ErrorMessage.Space.NOT_BLANK)
    private final String email;

    @NotBlank(message = ErrorMessage.Space.NOT_BLANK)
    private final String title;

    @NotBlank(message = ErrorMessage.Space.NOT_BLANK)
    private final String location;

    private final int capacity;

    @NotBlank(message = ErrorMessage.Space.NOT_BLANK)
    private final LocalDate startAt;

    @NotBlank(message = ErrorMessage.Space.NOT_BLANK)
    private final LocalDate endAt;

    @Builder
    public SpaceCreateRequest(final String email,
                              final String title,
                              final String location,
                              final int capacity,
                              final LocalDate startAt,
                              final LocalDate endAt) {
        this.email = email;
        this.title = title;
        this.location = location;
        this.capacity = capacity;
        this.startAt = startAt;
        this.endAt = endAt;
    }
}
