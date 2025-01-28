package com.bankapp.bank.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ClientCreateDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    public String getName() { return name; }

    public String getSurname() { return surname; }

    public String getEmail() { return email; }
}
