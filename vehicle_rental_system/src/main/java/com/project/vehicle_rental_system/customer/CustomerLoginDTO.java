package com.project.vehicle_rental_system.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerLoginDTO {
    private String customerEmail;
    private String customerPassword;
}
