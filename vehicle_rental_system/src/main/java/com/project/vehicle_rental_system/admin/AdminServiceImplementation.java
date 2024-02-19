package com.project.vehicle_rental_system.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImplementation implements AdminService{
    @Autowired
    private AdminRepository adminRepository;
}
