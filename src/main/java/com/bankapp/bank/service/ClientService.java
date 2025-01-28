package com.bankapp.bank.service;

import com.bankapp.bank.model.Client;
import com.bankapp.bank.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client getClient(long clientId) {
        return clientRepository.findById(clientId).orElseThrow(() -> new IllegalStateException("Client with id '" + clientId + "' doesn't exists!"));
    }

    public void createClient(Client client) {
        clientRepository.save(client);
    }

    public void deleteClient(long clientId) {
        clientExists(clientId);
        clientRepository.deleteById(clientId);
    }

    public void clientExists(long clientId) {
        boolean exists = clientRepository.existsById(clientId);
        if (!exists) {
            throw new IllegalStateException("Client with id '" + clientId + "' doesn't exists!");
        }
    }
}
