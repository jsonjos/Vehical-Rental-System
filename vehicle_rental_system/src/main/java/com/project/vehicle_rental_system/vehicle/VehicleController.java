package com.project.vehicle_rental_system.vehicle;

import com.project.vehicle_rental_system.vehicle.exceptions.DeleteVehicleException;
import com.project.vehicle_rental_system.vehicle.exceptions.NoActiveException;
import com.project.vehicle_rental_system.vehicle.exceptions.UpdateVehicleException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:4200"})
@RestController
@RequestMapping("home")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/post")
    public Vehicle addVehicle(@Valid @RequestBody Vehicle newVehicle){
        return vehicleService.addVehicle(newVehicle);
    }

    @GetMapping("/view")
    public Collection<Vehicle> viewVehicles(){
        return vehicleService.viewVehicles();
    }
    @PutMapping("update/{vehicleId}")
    public String updateVehicles(@Valid @PathVariable Integer vehicleId,@Valid @RequestBody Vehicle updatedVehicle) throws UpdateVehicleException {
        return vehicleService.updateVehicle(vehicleId,updatedVehicle);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteVehicle(@Valid @PathVariable Integer id) throws DeleteVehicleException {
        return vehicleService.deleteVehicle(id);
    }
    @GetMapping("/viewActive/{location}")
    public Collection<Vehicle> viewActiveVehicles(@Valid @PathVariable String location) throws NoActiveException {
        return vehicleService.viewActiveVehicles(location);
    }
}
