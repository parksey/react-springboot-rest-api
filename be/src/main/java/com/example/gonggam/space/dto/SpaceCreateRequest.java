package com.example.gonggam.space.dto;

import com.example.gonggam.util.ErrorMessage;
import com.example.gonggam.util.UtilsCode;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
public class SpaceCreateRequest {

    @NotBlank(message = ErrorMessage.Space.NOT_BLANK)
    private final String email;

    @NotBlank(message = ErrorMessage.Space.NOT_BLANK)
    private final String title;

    @NotBlank(message = ErrorMessage.Space.NOT_BLANK)
    private final String location;

    private String description;

    @Min(value = UtilsCode.Space.MIN_SPACE_CAPACITY, message = ErrorMessage.Space.UNDER_MIN_CAPACIRY)
    private final int capacity;

    @DateTimeFormat(pattern = UtilsCode.Space.DATE_FORMAT)
    @NotNull(message = ErrorMessage.Space.NO_DATA)
    private final LocalDateTime startAt;

    @DateTimeFormat(pattern = UtilsCode.Space.DATE_FORMAT)
    @NotNull(message = ErrorMessage.Space.NO_DATA)
    private final LocalDateTime endAt;

    @Builder
    public SpaceCreateRequest(final String email,
                              final String title,
                              final String location,
                              final String description,
                              final int capacity,
                              final LocalDateTime startAt,
                              final LocalDateTime endAt) {
        this.email = email;
        this.title = title;
        this.location = location;
        this.description = description;
        this.capacity = capacity;
        this.startAt = startAt;
        this.endAt = endAt;
    }
}
