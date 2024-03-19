package com.project.vehicle_rental_system;

import com.project.vehicle_rental_system.vehicle.Vehicle;
import com.project.vehicle_rental_system.vehicle.VehicleService;
import com.project.vehicle_rental_system.vehicle.exceptions.DeleteVehicleException;
import com.project.vehicle_rental_system.vehicle.exceptions.NoActiveException;
import com.project.vehicle_rental_system.vehicle.exceptions.UpdateVehicleException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VehicleServiceTest {
    @Autowired
    private VehicleService vehicleService;

    @DisplayName("New Vehicle Creation")
    @Test
    @Order(1)
    void newVehicleCreation() {
        try {
            Assertions.assertNotNull(vehicleService.addVehicle(new Vehicle(1, "Ford", 2020, true, 1000.0, "Chennai")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("Delete Vehicle")
    @Test
    @Order(2)
    void deleteVehicle()  {
        vehicleService.addVehicle(new Vehicle(1, "Ford", 2020, true, 1000.0, "Chennai"));
        try {
            Assertions.assertNotNull(vehicleService.deleteVehicle(1), "Vehicle");
        } catch (DeleteVehicleException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("View Active Vehicle Test")
    @Test
    @Order(3)
    void viewActiveVehicleTest()  {
        vehicleService.addVehicle(new Vehicle(1, "Ford", 2020, true, 1000.00, "Chennai"));
        try {
            Assertions.assertNotNull(vehicleService.viewActiveVehicles("Chennai"));
        } catch (NoActiveException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("Update vehicle")
    @Test
    @Order(4)
    void updateVehicleTest() {
        vehicleService.addVehicle(new Vehicle(1, "Ford", 2020, true, 1000.00, "Chennai"));
        try {
            Assertions.assertNotNull(vehicleService.updateVehicle(1, new Vehicle(1, "Ford Mustang", 2020, true, 1000.00, "Coimbatore")));
        } catch (UpdateVehicleException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("Update vehicle without existence")
    @Test
    @Order(5)
    void negativeUpdateVehicleTest() {
        Assertions.assertThrows(UpdateVehicleException.class, () -> vehicleService.updateVehicle(2, new Vehicle(1, "Ford Mustang", 2020, true, 1000.00, "Chennai")));
    }

    @DisplayName("View vehicles")
    @Test
    @Order(6)
    void viewVehicleTest() {
        Assertions.assertNotNull(vehicleService.viewVehicles());
    }

    @DisplayName("No active vehicles found Test")
    @Test
    void negativeViewActiveVehicle() {
        Assertions.assertThrows(NoActiveException.class, () -> vehicleService.viewActiveVehicles("bangalore"));
    }

    @DisplayName("Negative number input for deletion")
    @Test
    void negativeNumberDeletionTest() {
        Assertions.assertThrows(DeleteVehicleException.class, () -> vehicleService.deleteVehicle(-1));
    }

}
