package com.project.vehicle_rental_system.bank;

import com.project.vehicle_rental_system.bank.exceptions.BankAccountException;
import com.project.vehicle_rental_system.customer.Customer;
import com.project.vehicle_rental_system.customer.CustomerRepository;
import com.project.vehicle_rental_system.customer.exceptions.CustomerException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImplementation implements AccountService{
    final
    AccountRepository accountRepository;
    BankBalanceRepository bankBalanceRepository;
    CustomerRepository customerRepository;



    public AccountServiceImplementation(AccountRepository accountRepository,BankBalanceRepository bankBalanceRepository,CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.bankBalanceRepository=bankBalanceRepository;
        this.customerRepository=customerRepository;
    }

    @Override
    public Account addAccount(Integer customerId, AccountDto accountDto) throws BankAccountException, CustomerException {
        Optional<Customer> foundCustomer=customerRepository.findById(customerId);
        if(foundCustomer.isEmpty()){
            throw new CustomerException("Customer Not found");
        }
        Customer updateCustomer=foundCustomer.get();
        Account account=Account.builder().bankId(accountDto.getBankId()).bankPassword(accountDto.getBankPassword()).build();
        BankBalance bankBalance=bankBalanceRepository.findById(account.getBankId()).get();
        account.setBankBalance(bankBalance.getBalance());
        String password=bankBalance.getPassword();
        if(password.equals(account.getBankPassword())){
            accountRepository.save(account);
            updateCustomer.setCustomerAccount(account);
            customerRepository.save(updateCustomer);
            return account;
        }
//        account.setBankId(accountDto.getBankId());
//        account.setBankPassword(accountDto.getBankPassword());
//        return "Password incorrect";
        return null;
    }

    @Override
    public String addBankCredentials(BankBalance bankBalance) {
        bankBalanceRepository.save(bankBalance);
        return "bank creds added successfully";
    }



}
