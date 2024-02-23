package com.project.vehicle_rental_system.admin;

import com.project.vehicle_rental_system.bank.Account;
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
@Table(name = "Admin_Details")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Admin_ID")
    private Integer adminId;

    @Column(name = "Admin_Name")
    private String adminName;

    @Column(name = "Email_ID")
    private String adminEmail;

    @Column(name = "Password")
    private String adminPassword;


    @OneToOne
    private Account adminAccount;

}

