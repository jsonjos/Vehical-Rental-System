package com.project.vehicle_rental_system.bank;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDto {
    private Integer bankId;
    @NotBlank(message = "Enter Password")
    private String bankPassword;
}
