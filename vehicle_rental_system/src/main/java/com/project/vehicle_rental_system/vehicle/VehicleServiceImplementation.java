package com.project.vehicle_rental_system.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImplementation implements VehicleService{
    @Autowired
    private VehicleRepository vehicleRepository;
}
