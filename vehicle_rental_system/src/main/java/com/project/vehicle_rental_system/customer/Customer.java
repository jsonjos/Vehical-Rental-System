package com.project.vehicle_rental_system.customer;

import com.project.vehicle_rental_system.bank.Account;
import com.project.vehicle_rental_system.booking.Booking;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Customer_Details")
public class Customer {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "Customer_ID")
    private Integer customerId;

    @Column(name = "Customer_Name")
    private String customerName;

    @Column(name = "Email_ID")
    private String customerEmail;

    @Column(name = "Password")
    private String customerPassword;

    @OneToOne
    private Account customerAccount;


    @OneToMany
    private List<Booking> bookingList = new ArrayList<>();


    public Customer(String customerName, String customerEmail, String customerPassword) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPassword = customerPassword;
    }
}
