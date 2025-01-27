package com.bankapp.bank.controller;

import com.bankapp.bank.dto.BankAccountCreateDTO;
import com.bankapp.bank.mapper.BankAccountMapper;
import com.bankapp.bank.model.BankAccount;
import com.bankapp.bank.model.OperationType;
import com.bankapp.bank.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public void createBankAccount(@RequestBody BankAccountCreateDTO bankAccount) {
        BankAccount newBankAccount = bankAccountMapper.toEntity(bankAccount);
        bankAccountService.createBankAccount(newBankAccount);
    }

    @PostMapping("/deposit")
    public void depositToBankAccount(@RequestParam String accountNumber, @RequestParam double amount) {
        bankAccountService.changeBalance(OperationType.DEPOSIT, accountNumber, amount);
    }

    @PostMapping("/withdrawal")
    public void withdrawFromBankAccount(@RequestParam String accountNumber, @RequestParam double amount) {
        bankAccountService.changeBalance(OperationType.WITHDRAW, accountNumber, amount);
    }

    @DeleteMapping(path = "{bankAccountNumber}")
    public void deleteBankAccount(@PathVariable("bankAccountNumber") String accountNumber) {
        bankAccountService.deleteBankAccount(accountNumber);
    }

}
