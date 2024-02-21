package com.project.vehicle_rental_system.bank;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Account,Integer> {
}
