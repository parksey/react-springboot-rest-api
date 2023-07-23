package com.example.gonggam.reservation.dto;

import com.example.gonggam.util.exception.ValidationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReserveRequest {

   @NotNull(message = ValidationStatus.Global.NO_DATA)
   private Long spaceId;

   public ReserveRequest(final Long spaceId) {
       this.spaceId = spaceId;
   }

   protected ReserveRequest() {
   }
}
