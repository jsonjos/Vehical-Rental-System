package com.project.vehicle_rental_system.vehicle;

import com.project.vehicle_rental_system.vehicle.exceptions.DeleteVehicleException;
import com.project.vehicle_rental_system.vehicle.exceptions.NoActiveException;
import com.project.vehicle_rental_system.vehicle.exceptions.UpdateVehicleException;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("home")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/post")
    public Vehicle addVehicle(@RequestBody Vehicle newVehicle){
        return vehicleService.addVehicle(newVehicle);
    }

    @GetMapping("/view")
    public Collection<Vehicle> viewVehicles(){
        return vehicleService.viewVehicles();
    }
    @PostMapping("update/{vehicleId}")
    public String updateVehicles(@PathVariable Integer vehicleId,@RequestBody Vehicle updatedVehicle) throws UpdateVehicleException {
        return vehicleService.updateVehicle(vehicleId,updatedVehicle);
    }
    @DeleteMapping("/delete")
    public String deleteVehicle(@RequestBody Integer vehicleId) throws DeleteVehicleException {
        return vehicleService.deleteVehicle(vehicleId);
    }
    @GetMapping("/viewActive")
    public Collection<Vehicle> viewActiveVehicles(String location) throws NoActiveException {
        return vehicleService.viewActiveVehicles(location);
    }
}
