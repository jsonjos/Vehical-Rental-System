package com.project.vehicle_rental_system.bank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankBalanceRepository extends JpaRepository<BankBalance,Integer> {
}
