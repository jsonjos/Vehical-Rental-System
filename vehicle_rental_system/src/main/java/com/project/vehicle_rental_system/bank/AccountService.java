package com.project.vehicle_rental_system.bank;

import com.project.vehicle_rental_system.bank.exceptions.BankAccountException;
import com.project.vehicle_rental_system.customer.exceptions.CustomerException;

public interface AccountService {
    Account addAccount(Integer customerId,AccountDto accountDto) throws BankAccountException, CustomerException;
    String addBankCredentials(BankBalance bankBalance);
}
