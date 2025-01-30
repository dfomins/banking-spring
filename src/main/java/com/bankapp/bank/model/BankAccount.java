package com.bankapp.bank.model;

import com.bankapp.bank.utils.GeneratorUtils;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class BankAccount {
    @Id
    @Column(nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private final String accountNumber;
    private double balance;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @Getter(AccessLevel.NONE)
    private Client client;

    public BankAccount() {
        this.accountNumber = GeneratorUtils.generateCardNumber();
    }

    public String getClientFullName() { return client.getName() + " " + client.getSurname(); }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalStateException("Amount should be greater than 0");
        }

        balance += amount;
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
