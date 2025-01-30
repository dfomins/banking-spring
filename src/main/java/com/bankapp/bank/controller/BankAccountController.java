package com.bankapp.bank.controller;

import com.bankapp.bank.dto.BankAccountCreateDTO;
import com.bankapp.bank.dto.BankAccountDTO;
import com.bankapp.bank.dto.BankAccountPublicDTO;
import com.bankapp.bank.mapper.BankAccountMapper;
import com.bankapp.bank.model.BankAccount;
import com.bankapp.bank.model.OperationType;
import com.bankapp.bank.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/accounts")
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
    public List<BankAccountDTO> getBankAccounts() {
        return bankAccountService.getBankAccounts();
    }

    @GetMapping("/public")
    public List<BankAccountPublicDTO> getBankAccountsPublic() {
        return bankAccountService.getBankAccountsPublic();
    }

    @GetMapping(path = "{accountNumber}")
    public BankAccount getBankAccount(@PathVariable("accountNumber") String accountNumber) {
        return bankAccountService.getBankAccount(accountNumber);
    }

    @PostMapping
    public void createBankAccount(@RequestBody BankAccountCreateDTO bankAccount) {
        bankAccountService.createBankAccount(bankAccount);
    }

    @PutMapping("/deposit")
    public void depositToBankAccount(@RequestParam String accountNumber, @RequestParam double amount) {
        bankAccountService.changeBalance(OperationType.DEPOSIT, accountNumber, amount);
    }

    @PutMapping("/withdrawal")
    public void withdrawFromBankAccount(@RequestParam String accountNumber, @RequestParam double amount) {
        bankAccountService.changeBalance(OperationType.WITHDRAW, accountNumber, amount);
    }

    @PutMapping("/transfer/internal")
    public void transferToBankAccount(@RequestParam String senderAccountNumber, @RequestParam String receiverAccountNumber, @RequestParam double amount) {
        bankAccountService.internalTransfer(senderAccountNumber, receiverAccountNumber, amount);
    }

    @DeleteMapping(path = "{accountNumber}")
    public void deleteBankAccount(@PathVariable("accountNumber") String accountNumber) {
        bankAccountService.deleteBankAccount(accountNumber);
    }

}
