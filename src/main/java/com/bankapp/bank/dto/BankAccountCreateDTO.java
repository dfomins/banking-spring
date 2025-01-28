package com.bankapp.bank.dto;

import jakarta.validation.constraints.NotBlank;

public class BankAccountCreateDTO {
    private double balance;
    @NotBlank
    private long clientId;

    public double getBalance() { return balance; }
    public long getClientId() { return clientId; }
}
