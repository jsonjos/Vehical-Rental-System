package com.project.vehicle_rental_system.vehicle;

import com.project.vehicle_rental_system.vehicle.exceptions.DeleteVehicleException;
import com.project.vehicle_rental_system.vehicle.exceptions.NoActiveException;
import com.project.vehicle_rental_system.vehicle.exceptions.UpdateVehicleException;

import java.util.Collection;

public interface VehicleService {
    Vehicle addVehicle(Vehicle newvehicle);
    Collection<Vehicle> viewVehicles() ;
    String updateVehicle(Integer vehicleId,Vehicle updatedVehicle) throws UpdateVehicleException;
    String deleteVehicle(Integer vehicleID) throws  DeleteVehicleException;
    Collection<Vehicle> viewActiveVehicles(String location) throws  NoActiveException;

}
