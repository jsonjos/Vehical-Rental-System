package com.project.vehicle_rental_system.vehicle;

import com.project.vehicle_rental_system.vehicle.exceptions.DeleteVehicleException;
import com.project.vehicle_rental_system.vehicle.exceptions.NoActiveException;
import com.project.vehicle_rental_system.vehicle.exceptions.UpdateVehicleException;

import java.util.Collection;

public interface VehicleService {
    Vehicle addVehicle(Vehicle newVehicle);
    Collection<Vehicle> viewVehicles() ;
    void updateVehicle(Integer vehicleID,Vehicle updatedVehicle) throws UpdateVehicleException;
    void deleteVehicle(Integer vehicleId) throws  DeleteVehicleException;
    Collection<Vehicle> viewActiveVehicles(String location) throws  NoActiveException;

}
