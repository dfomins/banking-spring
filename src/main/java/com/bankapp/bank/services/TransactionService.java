package com.bankapp.bank.services;

import com.bankapp.bank.controller.TransactionController;
import com.bankapp.bank.model.BankAccount;
import com.bankapp.bank.model.Transaction;
import com.bankapp.bank.repository.BankAccountRepository;
import com.bankapp.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(BankAccountRepository bankAccountRepository, TransactionRepository transactionRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getAccountTransactions(String accountNumber) {
        BankAccount bankAccount = bankAccountRepository.findById(accountNumber).orElseThrow(() -> new IllegalStateException("Account with number '" + accountNumber + "' does not exists!"));
        return transactionRepository.findTransactionsByAccountNumber(accountNumber);
    }

}
