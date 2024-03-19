package com.project.vehicle_rental_system.bank;

import com.project.vehicle_rental_system.bank.exceptions.BankAccountException;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImplementation implements AccountService{
    final
    AccountRepository accountRepository;
    BankBalanceRepository bankBalanceRepository;

    public AccountServiceImplementation(AccountRepository accountRepository,BankBalanceRepository bankBalanceRepository) {
        this.accountRepository = accountRepository;
        this.bankBalanceRepository=bankBalanceRepository;
    }

    @Override
    public String addAccount(AccountDto accountDto) throws BankAccountException {
        Account account=Account.builder().bankId(accountDto.getBankId()).bankPassword(accountDto.getBankPassword()).build();
        BankBalance bankBalance=bankBalanceRepository.findById(account.getBankId()).get();
        account.setBankBalance(bankBalance.getBalance());
        String password=bankBalance.getPassword();
        if(password.equals(account.getBankPassword())){
            accountRepository.save(account);
            return "Account saved successfully";
        }
//        account.setBankId(accountDto.getBankId());
//        account.setBankPassword(accountDto.getBankPassword());
        return "Password incorrect";
    }

    @Override
    public String addBankCredentials(BankBalance bankBalance) {
        bankBalanceRepository.save(bankBalance);
        return "bank creds added successfully";
    }



}
