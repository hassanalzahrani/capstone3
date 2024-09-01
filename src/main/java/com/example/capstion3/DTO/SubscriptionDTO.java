package com.example.capstion3.DTO;

import jakarta.validation.constraints.Email;;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@AllArgsConstructor
public class SubscriptionDTO {
    private Integer user_id;
    @Pattern(regexp = "(subscribe|non-subscribe)+$")
    private String subscriptionType;
    @Email(message = "enter valid email")
    private String email_address;
    private LocalDate start_date=LocalDate.now();
    private LocalDate end_date=start_date.plus(1, ChronoUnit.MONTHS);
}
