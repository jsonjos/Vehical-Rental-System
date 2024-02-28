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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bookingId;
    @Column(name="No_Of_Days")
    private Integer noOfDays;

    @OneToOne
    private Vehicle vehicle;

    @OneToOne
    private Payment payment;

}
