package com.project.vehicle_rental_system.booking;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReturnDto {

    Integer vehicleId;

    @Size(min = 3 , max = 15, message = "Enter Valid Location")
    @NotBlank(message = "Enter Location")
    String vehicleLocation;

}
