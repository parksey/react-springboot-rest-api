package com.example.gonggam.space.domain;

import com.example.gonggam.space.exception.SharedSpaceException;
import com.example.gonggam.util.UtilsCode;
import com.example.gonggam.util.exception.CustomValidationStatus;
import com.example.gonggam.util.exception.ValidationStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class SharedSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spaceId;

    @Size(max = 50, message = ValidationStatus.Space.OVER_SIZE)
    @NotNull(message = ValidationStatus.Global.NO_DATA)
    private String title;

    private String description;

    @Size(max = 100, message = ValidationStatus.Space.OVER_SIZE)
    @NotNull(message = ValidationStatus.Global.NO_DATA)
    private String location;

    @Min(value = 0, message = ValidationStatus.Space.UNDER_SIZE)
    private Integer capacity;

    @Min(value = UtilsCode.Space.MIN_MONEY, message = ValidationStatus.Space.UNDER_MIN_MONEY)
    @NotNull
    private long amount;


    @NotNull(message = ValidationStatus.Global.NO_DATA)
    private LocalDateTime startAt;

    @NotNull(message = ValidationStatus.Global.NO_DATA)
    private LocalDateTime endAt;

    @NotNull(message = ValidationStatus.Global.NO_DATA)
    private Long ownerId;

    private SharedSpace(final Long spaceId,
                        final String title,
                        final String description,
                        final String location,
                        final Integer capacity,
                        final long amount,
                        final LocalDateTime startAt,
                        final LocalDateTime endAt,
                        Long ownerId) {
        validateTime(startAt, endAt);
        this.spaceId = spaceId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.capacity = validateAndGetDefaultCapacity(capacity);
        this.amount = amount;
        this.startAt = startAt;
        this.endAt = endAt;
        this.ownerId = ownerId;
    }

    @Builder
    public SharedSpace (String title,
                       String description,
                       String location,
                       Integer capacity,
                       long amount,
                       LocalDateTime startAt,
                       LocalDateTime endAt,
                       Long ownerId) {
        this(null, title, description, location, capacity, amount, startAt, endAt, ownerId);
    }

    protected SharedSpace() {
    }

    private Integer validateAndGetDefaultCapacity(Integer capacity) {
        if (capacity == null) {
            return UtilsCode.Space.MIN_SPACE_CAPACITY;
        }
        return capacity;
    }

    private void validateTime(LocalDateTime startAt, LocalDateTime endAt) {
        if (startAt.isAfter(endAt)) {
            throw new SharedSpaceException(CustomValidationStatus.TIME_ERROR);
        }
    }
}
