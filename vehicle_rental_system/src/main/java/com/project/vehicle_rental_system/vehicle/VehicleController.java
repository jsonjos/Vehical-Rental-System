package com.project.vehicle_rental_system.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
}
