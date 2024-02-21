package com.project.vehicle_rental_system.vehicle;

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
    public void updateVehicle(Integer vehicleID, Vehicle updatedVehicle) {
        Optional<Vehicle> foundVehicle=vehicleRepository.findById(vehicleID);
//        if(foundVehicle.isEmpty()){
//            throw new VehicleException("Vehicle with ID : "+vehicleID+" not found for updation");
//        }
        Vehicle tempVehicle = foundVehicle.get();
        tempVehicle.setIsAvailable(updatedVehicle.getIsAvailable());
        tempVehicle.setVehicleLocation(updatedVehicle.getVehicleLocation());
        tempVehicle.setRent(updatedVehicle.getRent());
        tempVehicle.setModelName(updatedVehicle.getModelName());
        tempVehicle.setModelYear(updatedVehicle.getModelYear());
        vehicleRepository.save(tempVehicle);

    }


    @Override
    public void deleteVehicle(Integer vehicleId) {
        vehicleRepository.deleteById(vehicleId);

    }

    @Override
    public Collection<Vehicle> viewActiveVehicles(String location) {
        List<Vehicle> activeVehicleList = new ArrayList<>(vehicleRepository.findAll());
        return activeVehicleList.stream().filter
                (s -> s.getIsAvailable() == Boolean.TRUE &&
                        s.getVehicleLocation().equals(location))
                .collect(Collectors.toList());
    }


}


