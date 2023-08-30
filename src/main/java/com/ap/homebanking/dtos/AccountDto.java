package com.ap.homebanking.dtos;

import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Transaction;

import java.time.LocalDate;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class AccountDto {

    private long id;
    private String number;
    private LocalDate creationDate;
    private double balance;
    private Set<Transaction> transactions;

    public AccountDto() {
    }

    public AccountDto(Account account) {
        this.id = account.getId();
        this.number = account.getNumber();
        this.creationDate = account.getCreationDate();
        this.balance = account.getBalance();
        this.transactions = account.getTransactions();
    }

    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public double getBalance() {
        return balance;
    }

    public Set<TransactionDto> getTransactions() {
        return transactions.stream().map(transaction -> new TransactionDto(transaction)).collect(toSet());
    }
}