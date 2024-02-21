package com.project.vehicle_rental_system.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("home")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    @PostMapping("/post")
    public Vehicle addVehicle(@RequestBody Vehicle newVehicle){
        return vehicleService.addVehicle(newVehicle);
    }

    @GetMapping("/view")
    public Collection<Vehicle> viewVehicles(){
        return vehicleService.viewVehicles();
    }
    @PostMapping("update/{vehicleId}")
    public void updateVehicles(@PathVariable Integer vehicleId,@RequestBody Vehicle updatedVehicle){
        vehicleService.updateVehicle(vehicleId,updatedVehicle);
    }
    @DeleteMapping("/delete")
    public void deleteVehicle(@RequestBody Integer vehicleId){
        vehicleService.deleteVehicle(vehicleId);
    }
    @GetMapping("/viewActive")
    public Collection<Vehicle> viewActiveVehicles(String location){
        return vehicleService.viewActiveVehicles(location);
    }
}
