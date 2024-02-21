package com.project.vehicle_rental_system.booking;

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
    String vehicleLocation;

}
