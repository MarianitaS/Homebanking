package com.ap.homebanking.dtos;

import com.ap.homebanking.models.Account;

import java.time.LocalDate;

public class AccountDto {

    private int id;
    private String number;
    private LocalDate creationDate;
    private long balance;


    public AccountDto() {
    }

    public AccountDto(Account account) {
        this.id = account.getId();
        this.number = account.getNumber();
        this.creationDate = account.getCreationDate();
        this.balance = account.getBalance();
    }

    public int getId() {
        return id;
    }
    public String getNumber() {
        return number;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }
    public long getBalance() {
        return balance;
    }
}
