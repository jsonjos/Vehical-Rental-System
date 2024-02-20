package com.project.vehicle_rental_system.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDto {
    private Integer customerId;
    private String customerName;
    private String customerEmail;
    private String customerPassword;

}
