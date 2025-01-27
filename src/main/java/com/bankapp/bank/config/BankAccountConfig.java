package com.bankapp.bank.config;

import com.bankapp.bank.model.BankAccount;
import com.bankapp.bank.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BankAccountConfig {

    @Bean
    CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository) {
        return args -> {

            BankAccount ba1 = new BankAccount();
            BankAccount ba2 = new BankAccount(
                    120.11
            );

            bankAccountRepository.saveAll(
                    List.of(ba1, ba2)
            );

        };
    }

}
