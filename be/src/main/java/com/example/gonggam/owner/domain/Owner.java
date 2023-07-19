package com.example.gonggam.owner.domain;

import com.example.gonggam.util.ErrorMessage;
import com.example.gonggam.util.UtilsCode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ownerId;

    @NotBlank(message = ErrorMessage.Owner.NO_OWNER_NUMBER)
    private String ownerNo;

    @Pattern(regexp = UtilsCode.Global.PHONE_PATTERN, message = ErrorMessage.Global.NOT_PHONE_PATTERN)
    @NotNull
    private String phone;

    @Email(regexp = UtilsCode.Global.EMAIL_PATTERN,message = ErrorMessage.Global.NOT_EMAIL)
    @NotNull
    private String email;

    @NotNull(message = ErrorMessage.Global.NO_DATA)
    private LocalDateTime createAt;

    private Owner(Long ownerId, String ownerNo, String phone, String email, LocalDateTime createAt) {
        this.ownerId = ownerId;
        this.ownerNo = ownerNo;
        this.phone = phone;
        this.email = email;
        this.createAt = createAt;
    }

    @Builder
    public Owner(String ownerNo, String phone, String email, LocalDateTime createAt) {
        this(null, ownerNo, phone, email, createAt);
    }

    protected Owner() {
    }
}
