package com.project.vehicle_rental_system.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImplementation implements AccountService{
    final
    AccountRepository accountRepository;

    public AccountServiceImplementation(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public String addAccount(AccountDto accountDto) {
        Account account=Account.builder().bankId(accountDto.getBankId()).bankPassword(accountDto.getBankPassword()).build();
//        account.setBankId(accountDto.getBankId());
//        account.setBankPassword(accountDto.getBankPassword());
        accountRepository.save(account);
        return "Account saved successfully";
    }
//    Account account=new Account();
//        account.setBankId(accountDto.getBankId());
//        account.setBankPassword(accountDto.getBankPassword());
//        accountRepository.save(account);
//        return "Account saved successfully";
//}


}
