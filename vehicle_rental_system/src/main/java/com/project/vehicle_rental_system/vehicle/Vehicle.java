package com.project.vehicle_rental_system.vehicle;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Vehicle {
    @Id
    @Column(name = "Vehicle_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer vehicleId;

    @Column(name = "Model_Name")
    @Size(min = 3, max = 15 , message = "Enter Valid Model Name")
    @NotBlank(message = "Enter Model Name")
    private String modelName;

    @Column(name = "Model_Year")
    @Min(1990)
    @Max(2024)
    private Integer modelYear;

    @Column(name = "Availability")
    private Boolean isAvailable;

    @Column(name = "Vehicle_Rent")
    @Min(0)
    private Double rent;

    @Column(name = "Vehicle_Location")
    @Size(min = 3, max = 15 , message = "Enter Valid Location")
    @NotBlank(message = "Enter Location")
    private String vehicleLocation;


}
