package com.project.vehicle_rental_system.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeleteCustomerDto {
    @Email
    private String customerEmail;
    @NotBlank(message = "Enter Password")
    private String customerPassword;
}
