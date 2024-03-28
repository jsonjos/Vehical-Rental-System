package com.project.vehicle_rental_system.booking;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.vehicle_rental_system.customer.Customer;
import com.project.vehicle_rental_system.customer.CustomerRepository;
import com.project.vehicle_rental_system.payment.Payment;
import com.project.vehicle_rental_system.vehicle.Vehicle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bookingId;

    private Boolean bookingFlag;

    private String bookingStatus;

    @ManyToOne
    private Vehicle vehicle;

    @OneToOne
    private Payment payment;
    @ManyToOne
    @JsonIgnore
    private Customer customer;

}
