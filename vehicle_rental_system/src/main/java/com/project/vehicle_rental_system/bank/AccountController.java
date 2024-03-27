package com.project.vehicle_rental_system.bank;
import com.project.vehicle_rental_system.bank.exceptions.BankAccountException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:4200/")
public class AccountController {
    private final AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("account/add")
    public String addAccount(@Valid @RequestBody AccountDto accountDto) throws BankAccountException {
        return this.accountService.addAccount(accountDto);
    }
    @PostMapping("bank/create")
    public String addBankCredentials(@Valid @RequestBody BankBalance bankBalance){
        return this.accountService.addBankCredentials(bankBalance);
    }
}
