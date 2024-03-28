package com.project.vehicle_rental_system.bank;
import com.project.vehicle_rental_system.bank.exceptions.BankAccountException;
import com.project.vehicle_rental_system.customer.exceptions.CustomerException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"})
@RestController
public class AccountController {
    private final AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("account/add/{customerId}")
    public Account addAccount(@PathVariable Integer customerId, @Valid @RequestBody AccountDto accountDto) throws BankAccountException, CustomerException {
        return this.accountService.addAccount(customerId,accountDto);
    }
    @PostMapping("bank/create")
    public String addBankCredentials(@Valid @RequestBody BankBalance bankBalance){
        return this.accountService.addBankCredentials(bankBalance);
    }
}
