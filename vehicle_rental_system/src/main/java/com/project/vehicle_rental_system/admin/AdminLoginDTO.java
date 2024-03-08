package com.project.vehicle_rental_system.admin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminLoginDTO {
    @Email(message = "Enter Valid Mail ID")
    @NotBlank(message = "Enter Mail ID")
    private String adminEmail;

    @NotBlank(message = "Enter Password")
    private String adminPassword;
}
