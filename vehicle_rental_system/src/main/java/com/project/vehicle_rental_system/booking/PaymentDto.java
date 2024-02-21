package com.project.vehicle_rental_system.booking;

import com.project.vehicle_rental_system.bank.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentDto {
    Integer bookingId;
    Integer customerAccountId;
}
