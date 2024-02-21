package com.project.vehicle_rental_system.vehicle;
import java.util.Collection;

public interface VehicleService {
    Vehicle addVehicle(Vehicle newVehicle);
    Collection<Vehicle> viewVehicles() ;
    void updateVehicle(Integer vehicleID,Vehicle updatedVehicle) throws VehicleException;
    void deleteVehicle(Integer vehicleId) ;
    Collection<Vehicle> viewActiveVehicles(String location) ;

}
