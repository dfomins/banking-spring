package com.bankapp.bank.service;

import com.bankapp.bank.model.BankAccount;
import com.bankapp.bank.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccountRepository.findAll();
    }

    public void createBankAccount(BankAccount bankAccount) {
//        Optional<BankAccount> bankAccountByNumber = bankAccountRepository.findBankAccountByNumber(bankAccount.getAccountNumber());
//        if (bankAccountByNumber.isPresent()) {
//            throw new IllegalStateException("Number already exists");
//        }

        bankAccountRepository.save(bankAccount);
    }

    public void depositToBankAccount(String accountNumber, double amount) {
        bankAccountExists(accountNumber);
        BankAccount bankAccount = bankAccountRepository.findByAccountNumber(accountNumber);
        bankAccount.deposit(amount);
        bankAccountRepository.save(bankAccount);
    }

    public void deleteBankAccount(String accountNumber)
    {
        bankAccountExists(accountNumber);
        bankAccountRepository.deleteById(accountNumber);
    }

    public void bankAccountExists(String accountNumber) {
        boolean exists = bankAccountRepository.existsById(accountNumber);
        if (!exists) {
            throw new IllegalStateException("Account with number '" + accountNumber + "' does not exists!");
        }
    }

}
