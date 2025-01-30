package com.bankapp.bank.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String operationType;

    private String senderAccountNumber;

    @NonNull
    private String receiverAccountNumber;

    @NonNull
    private Double amount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date transactionDate = new Date();

}
