package com.bankapp.bank.controller;

import com.bankapp.bank.model.Transaction;
import com.bankapp.bank.services.BankAccountService;
import com.bankapp.bank.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getTransactions() {
        return transactionService.getTransactions();
    }

    @GetMapping(path = "{accountNumber}")
    public List<Transaction> getAccountTransactions(@PathVariable("accountNumber") String accountNumber) {
        return transactionService.getAccountTransactions(accountNumber);
    }

}
