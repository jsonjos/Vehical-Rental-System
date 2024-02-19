package com.project.vehicle_rental_system.customer;

import com.project.vehicle_rental_system.bank.Account;
import com.project.vehicle_rental_system.booking.Booking;
import com.project.vehicle_rental_system.payment.Payment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Customer_Details")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Customer_ID")
    private Integer customerId;

    @Column(name="Customer_Name")
    private String customerName;

    @Column(name="Email_ID")
    private String customerEmail;

    @Column(name="Password")
    private String customerPassword;

    @OneToOne
    private Account customerAccount;


    @OneToMany
    private List<Booking> bookingList=new ArrayList<>();


}
