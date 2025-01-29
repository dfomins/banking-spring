package com.bankapp.bank.controller;

import com.bankapp.bank.dto.ClientCreateDTO;
import com.bankapp.bank.mapper.ClientMapper;
import com.bankapp.bank.model.Client;
import com.bankapp.bank.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "client")
@Validated
public class ClientController {
    private final ClientService clientService;

    private final ClientMapper clientMapper;

    @Autowired
    public ClientController(ClientService clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @GetMapping
    public List<Client> getClients() {
        return clientService.getClients();
    }

    @GetMapping(path = "{clientId}")
    public Client getClient(@PathVariable("clientId") long clientId) {
        return clientService.getClient(clientId);
    }

    @PostMapping
    public void createClient(@Valid @RequestBody ClientCreateDTO clientDTO) {
        Client client = clientMapper.toEntity(clientDTO);
        clientService.createClient(client);
    }

    @DeleteMapping(path = "{clientId}")
    public void deleteBankAccount(@PathVariable("clientId") long clientId) {
        clientService.deleteClient(clientId);
    }
}
