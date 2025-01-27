package com.bankapp.bank.controller;

import com.bankapp.bank.dto.BankAccountCreateDTO;
import com.bankapp.bank.mapper.BankAccountMapper;
import com.bankapp.bank.model.BankAccount;
import com.bankapp.bank.model.OperationType;
import com.bankapp.bank.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "bank_accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    // Mapper
    @Autowired
    private BankAccountMapper bankAccountMapper;

    @GetMapping
    public List<BankAccount> getBankAccounts() {
        return bankAccountService.getBankAccounts();
    }

    @GetMapping(path = "{accountNumber}")
    public Optional<BankAccount> getBankAccount(@PathVariable("accountNumber") String accountNumber) {
        return bankAccountService.getBankAccount(accountNumber);
    }

    @PostMapping
    public void createBankAccount(@RequestBody BankAccountCreateDTO bankAccount) {
        BankAccount newBankAccount = bankAccountMapper.toEntity(bankAccount);
        bankAccountService.createBankAccount(newBankAccount);
    }

    @PutMapping("/deposit")
    public void depositToBankAccount(@RequestParam String accountNumber, @RequestParam double amount) {
        bankAccountService.changeBalance(OperationType.DEPOSIT, accountNumber, amount);
    }

    @PutMapping("/withdrawal")
    public void withdrawFromBankAccount(@RequestParam String accountNumber, @RequestParam double amount) {
        bankAccountService.changeBalance(OperationType.WITHDRAW, accountNumber, amount);
    }

    @PutMapping("/transfer")
    public void transferToBankAccount(@RequestParam String fromAccountNumber, @RequestParam String toAccountNumber, @RequestParam double amount) {
        bankAccountService.transferToBankAccount(fromAccountNumber, toAccountNumber, amount);
    }

    @DeleteMapping(path = "{accountNumber}")
    public void deleteBankAccount(@PathVariable("accountNumber") String accountNumber) {
        bankAccountService.deleteBankAccount(accountNumber);
    }

}
