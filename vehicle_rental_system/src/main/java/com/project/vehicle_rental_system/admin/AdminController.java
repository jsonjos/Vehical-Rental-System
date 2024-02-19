package com.project.vehicle_rental_system.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class AdminController {
    @Autowired
    private AdminService adminService;
}
