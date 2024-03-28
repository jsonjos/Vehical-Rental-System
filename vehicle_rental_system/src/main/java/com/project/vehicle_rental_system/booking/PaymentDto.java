package com.project.vehicle_rental_system.booking;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentDto {

//    Integer bookingId;

    @Min(1)
    Integer noOfDays;

    Integer customerAccountId;
}
