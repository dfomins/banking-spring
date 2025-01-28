package com.bankapp.bank.dto;

public class BankAccountDTO {
    private final String clientFullName;
    private final String accountNumber;
    private final double balance;

    public BankAccountDTO(String clientFullName, String accountNumber, double balance) {
        this.clientFullName = clientFullName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getClientName() { return clientFullName; }

    public String getAccountNumber() { return accountNumber; }

    public double getBalance() { return balance; }
}
