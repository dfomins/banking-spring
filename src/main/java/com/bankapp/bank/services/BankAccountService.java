package com.bankapp.bank.services;

import com.bankapp.bank.dto.BankAccountCreateDTO;
import com.bankapp.bank.dto.BankAccountDTO;
import com.bankapp.bank.dto.BankAccountPublicDTO;
import com.bankapp.bank.model.BankAccount;
import com.bankapp.bank.model.Client;
import com.bankapp.bank.model.OperationType;
import com.bankapp.bank.model.Transaction;
import com.bankapp.bank.repository.BankAccountRepository;
import com.bankapp.bank.repository.ClientRepository;
import com.bankapp.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transferRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public BankAccountService(BankAccountRepository bankAccountRepository, TransactionRepository transferRepository, ClientRepository clientRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.transferRepository = transferRepository;
        this.clientRepository = clientRepository;
    }

    public List<BankAccountDTO> getBankAccounts() {
        return bankAccountRepository.findAll()
                .stream()
                .map(bankAccount -> new BankAccountDTO(
                        bankAccount.getClientFullName(),
                        bankAccount.getAccountNumber(),
                        bankAccount.getBalance()
                ))
                .collect(Collectors.toList());
    }

    public List<BankAccountPublicDTO> getBankAccountsPublic() {
        return bankAccountRepository.findAll()
                .stream()
                .map(bankAccount -> new BankAccountPublicDTO(
                        bankAccount.getAccountNumber(),
                        bankAccount.getBalance()
                ))
                .collect(Collectors.toList());
    }

    public BankAccount getBankAccount(String accountNumber) {
        return bankAccountRepository.findById(accountNumber).orElseThrow(() -> new IllegalStateException("Bank account doesn't exists!"));
    }

    public void createBankAccount(BankAccountCreateDTO bankAccountDTO) {
        Client client = clientRepository.findById(bankAccountDTO.getClientId()).orElseThrow(() -> new IllegalStateException("Client with id '" + bankAccountDTO.getClientId() + "' does not exists!"));
        BankAccount newBankAccount = new BankAccount();
        newBankAccount.setBalance(bankAccountDTO.getBalance());
        newBankAccount.setClient(client);

        bankAccountRepository.save(newBankAccount);
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

            default -> throw new IllegalStateException("Error");
        }

        bankAccountRepository.save(bankAccount);

        Transaction transaction = new Transaction(operationType.toString(), accountNumber, amount);
        transferRepository.save(transaction);
    }

    public void internalTransfer(String senderAccountNumber, String receiverAccountNumber, double amount) {
        bankAccountExists(senderAccountNumber);
        bankAccountExists(receiverAccountNumber);

        BankAccount senderBankAccount = bankAccountRepository.findByAccountNumber(senderAccountNumber);
        BankAccount receiverBankAccount = bankAccountRepository.findByAccountNumber(receiverAccountNumber);

        senderBankAccount.withdraw(amount);
        receiverBankAccount.deposit(amount);

        bankAccountRepository.saveAll(
                List.of(senderBankAccount, receiverBankAccount)
        );

        Transaction transaction = new Transaction((OperationType.TRANSFER).toString(), receiverAccountNumber, amount);
        transaction.setSenderAccountNumber(senderAccountNumber);
        transferRepository.save(transaction);
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
