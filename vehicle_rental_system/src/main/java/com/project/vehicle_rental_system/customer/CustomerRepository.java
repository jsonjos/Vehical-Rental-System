package com.project.vehicle_rental_system.customer;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
   Optional<Customer> findByCustomerEmail(String customerEmail);
   Optional<Customer> findByCustomerId(Integer customerId);
}
