package com.project.vehicle_rental_system.customer;

import com.project.vehicle_rental_system.bank.Account;
import com.project.vehicle_rental_system.booking.Booking;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
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
    @Size(min = 3, max = 15 , message = "Enter Valid Name")
    private String customerName;

    @Column(name = "Email_ID")
    @Email
    private String customerEmail;

    @Column(name = "Password")
    @NotBlank(message = "Enter Password")
    private String customerPassword;
    @Column(name="Status")
    private Boolean accountStatus;

    @OneToOne
    private Account customerAccount;


    @OneToMany
    private List<Booking> bookingList = new ArrayList<>();


}
