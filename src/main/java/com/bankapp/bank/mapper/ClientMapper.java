package com.bankapp.bank.mapper;

import com.bankapp.bank.dto.BankAccountCreateDTO;
import com.bankapp.bank.dto.ClientCreateDTO;
import com.bankapp.bank.model.BankAccount;
import com.bankapp.bank.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public Client toEntity(ClientCreateDTO dto) {
        Client client = new Client();
        client.setName(dto.getName());
        client.setSurname(dto.getSurname());
        client.setEmail(dto.getEmail());

        return client;
    }
}
