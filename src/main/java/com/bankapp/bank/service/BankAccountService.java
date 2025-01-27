package com.bankapp.bank.service;

import com.bankapp.bank.model.BankAccount;
import com.bankapp.bank.model.OperationType;
import com.bankapp.bank.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Optional<BankAccount> getBankAccount(String accountNumber) {
        bankAccountExists(accountNumber);
        return bankAccountRepository.findById(accountNumber);
    }

    public void createBankAccount(BankAccount bankAccount) {
        bankAccountRepository.save(bankAccount);
    }

    public void changeBalance(OperationType operationType, String accountNumber, double amount) {
        bankAccountExists(accountNumber);
        BankAccount bankAccount = bankAccountRepository.findByAccountNumber(accountNumber);

        if (amount <= 0) {
            throw new IllegalStateException("Amount should be greater than 0");
        }

        switch (operationType) {
            case DEPOSIT -> {
                bankAccount.deposit(amount);
            }

            case WITHDRAW -> {
                if (bankAccount.getBalance() < amount) {
                    throw new IllegalStateException("Not enough money!");
                }
                bankAccount.withdraw(amount);
            }
        }

        bankAccountRepository.save(bankAccount);
    }

    public void transferToBankAccount(String fromAccountNumber, String toAccountNumber, double amount) {
        bankAccountExists(fromAccountNumber);
        bankAccountExists(toAccountNumber);

        BankAccount fromBankAccount = bankAccountRepository.findByAccountNumber(fromAccountNumber);
        BankAccount toBankAccount = bankAccountRepository.findByAccountNumber(toAccountNumber);

        fromBankAccount.withdraw(amount);
        toBankAccount.deposit(amount);

        bankAccountRepository.saveAll(
                List.of(fromBankAccount, toBankAccount)
        );
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
