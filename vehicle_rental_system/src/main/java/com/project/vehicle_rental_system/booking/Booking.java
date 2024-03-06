package com.project.vehicle_rental_system.booking;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer bookingId;

    @ManyToOne
    private Vehicle vehicle;

    @OneToOne
    private Payment payment;

}
