package com.project.vehicle_rental_system.bank;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Bank_Balance")
public class BankBalance {
    @Id
    private Integer bankId;

    private Double balance;

    @NotBlank(message = "Password cannot be blank")
    private String password;

}
