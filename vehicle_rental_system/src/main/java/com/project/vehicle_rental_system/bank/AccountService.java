package com.project.vehicle_rental_system.bank;

import com.project.vehicle_rental_system.bank.exceptions.BankAccountException;

public interface AccountService {
    String addAccount(AccountDto accountDto) throws BankAccountException;
    String addBankCredentials(BankBalance bankBalance);
}
