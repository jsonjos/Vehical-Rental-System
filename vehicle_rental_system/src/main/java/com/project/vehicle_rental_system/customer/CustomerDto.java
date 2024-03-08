package com.project.vehicle_rental_system.customer;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDto {


    @Size(min = 3, max = 15 , message = "Enter Valid Name")
    private String customerName;

    @Email
    private String customerEmail;

    @NotBlank(message = "Enter Password")
    private String customerPassword;
}
