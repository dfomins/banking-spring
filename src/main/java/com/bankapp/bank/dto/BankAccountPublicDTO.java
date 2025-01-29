package com.bankapp.bank.dto;

public class BankAccountPublicDTO {
    private final String accountNumber;
    private final double balance;

    public BankAccountPublicDTO(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() { return accountNumber; }

    public double getBalance() { return balance; }
}
