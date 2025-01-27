package com.bankapp.bank.mapper;

import com.bankapp.bank.dto.BankAccountCreateDTO;
import com.bankapp.bank.model.BankAccount;
import org.springframework.stereotype.Component;

@Component
public class BankAccountMapper {
    public BankAccount toEntity(BankAccountCreateDTO dto) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(dto.getBalance());

        return bankAccount;
    }
}
