package com.example.gonggam.space.domain;

import com.example.gonggam.space.exception.SharedSpaceException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDateTime;

@Entity
public class SharedSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spaceId;

    @Size(max = 50, message = "{error.global.LONG-INPUT}")
    @NotNull(message = "error.global.NO-DATA")
    private String title;

    private String description;

    @Size(max = 100, message = "{error.global.LONG-INPUT}")
    @NotNull(message = "값이 안들어 왔습니다.")
    private String location;


    @Min(value = 1, message = "{error.global.UNDER-MIN}")
    private int capacity;

    @NotNull(message = "값이 안들어 왔습니다.")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startAt;

    @NotNull(message = "값이 안들어 왔습니다.")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endAt;


    @Builder
    public SharedSpace(String title, String description, String location, int capacity, LocalDateTime startAt, LocalDateTime endAt) {
        validateTime(startAt, endAt);
        this.title = title;
        this.description = description;
        this.location = location;
        this.capacity = capacity;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    protected SharedSpace() {
    }

    private void validateTime(LocalDateTime startAt, LocalDateTime endAt) {
        if (startAt.isAfter(endAt)) {
            throw new SharedSpaceException("시작일자와 종료일자가 잘못되었습니다.");
        }
    }
}
