package com.project.vehicle_rental_system.vehicle;

import com.project.vehicle_rental_system.vehicle.exceptions.DeleteVehicleException;
import com.project.vehicle_rental_system.vehicle.exceptions.NoActiveException;
import com.project.vehicle_rental_system.vehicle.exceptions.UpdateVehicleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImplementation implements VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle addVehicle(Vehicle newVehicle) {
        return vehicleRepository.save(newVehicle);
    }

    @Override
    public Collection<Vehicle> viewVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public void updateVehicle(Integer vehicleID, Vehicle updatedVehicle) throws UpdateVehicleException {
        Optional<Vehicle> foundVehicle=vehicleRepository.findById(vehicleID);
        if(foundVehicle.isEmpty()){
            throw new UpdateVehicleException("Vehicle with ID : "+vehicleID+" not found for update.");
        }
        if(updatedVehicle.getModelName()==null){
            throw new UpdateVehicleException("Enter valid model name");
        }
        if(updatedVehicle.getModelYear()==null){
            throw new UpdateVehicleException("Enter valid model year");
        }
        if(updatedVehicle.getVehicleLocation().equalsIgnoreCase("chennai") || updatedVehicle.getVehicleLocation().equalsIgnoreCase("bangalore") || updatedVehicle.getVehicleLocation().equalsIgnoreCase("bangladesh")){
            throw new UpdateVehicleException("Enter valid location");
        }
        Vehicle tempVehicle = foundVehicle.get();
        tempVehicle.setIsAvailable(updatedVehicle.getIsAvailable());
        tempVehicle.setVehicleLocation(updatedVehicle.getVehicleLocation());
        tempVehicle.setRent(updatedVehicle.getRent());
        tempVehicle.setModelName(updatedVehicle.getModelName());
        tempVehicle.setModelYear(updatedVehicle.getModelYear());
        vehicleRepository.save(tempVehicle);

    }

    @Override
    public void deleteVehicle(Integer vehicleID) throws DeleteVehicleException {
        Optional<Vehicle> foundVehicle=vehicleRepository.findById(vehicleID);
        if(foundVehicle.isEmpty()){
            throw new DeleteVehicleException("Vehicle with ID : "+vehicleID+" not found for deletion");
        }
        if(vehicleID<=0){
            throw new DeleteVehicleException("Enter a valid Id for deletion");
        }
        vehicleRepository.deleteById(vehicleID);

    }
    @Override
    public Collection<Vehicle> viewActiveVehicles(String location) throws NoActiveException {
        List<Vehicle> activeVehicleList = new ArrayList<>(vehicleRepository.findAll());
        if(activeVehicleList.isEmpty()){
            throw new NoActiveException("No active vehicles found.");
        }
        return activeVehicleList.stream().filter
                        (s -> s.getIsAvailable() == Boolean.TRUE &&
                                s.getVehicleLocation().equals(location))
                .collect(Collectors.toList());
    }

}


