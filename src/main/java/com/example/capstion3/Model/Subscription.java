package com.example.capstion3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Subscription {
    @Id
    private Integer id;
    @Column(columnDefinition ="varchar(15) not null")
    @Pattern(regexp = "(subscribe|non-subscribe)+$")
    private String subscriptionType;
    @Email(message = "Enter valid email")
    @Column(columnDefinition = "varchar(35) not null")
    private String email_address;
    @Column(columnDefinition = "date")
    private LocalDate start_date=LocalDate.now();
    @Column(columnDefinition = "date ")
    private LocalDate end_date=start_date.plus(1, ChronoUnit.MONTHS);

    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;
}
