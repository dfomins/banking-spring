package com.bankapp.bank.model;

import com.bankapp.bank.utils.GeneratorUtils;
import jakarta.persistence.*;

@Entity
@Table
public class BankAccount {
    @Id
    private final String accountNumber;
    private double balance;

    public BankAccount() {
        this.accountNumber = GeneratorUtils.generateCardNumber();
    }

    public BankAccount(double balance) {
        this.accountNumber = GeneratorUtils.generateCardNumber();
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            throw new IllegalStateException("Amount should be greater than 0");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalStateException("Amount should be greater than 0");
        }

        if (balance < amount) {
            throw new IllegalStateException("Not enough money");
        }

        balance -= amount;
    }
}
