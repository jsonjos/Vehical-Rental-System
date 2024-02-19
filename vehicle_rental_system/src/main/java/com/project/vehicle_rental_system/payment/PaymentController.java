package com.project.vehicle_rental_system.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
}
