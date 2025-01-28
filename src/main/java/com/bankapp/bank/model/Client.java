package com.bankapp.bank.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String surname;

    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BankAccount> bankAccounts;

    public long getClientId() { return id; }

    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() { return surname; }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        this.email = email;
    }

    public List<BankAccount> getBankAccounts() { return bankAccounts; }
}
