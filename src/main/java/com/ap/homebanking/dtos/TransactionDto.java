package com.ap.homebanking.dtos;

import com.ap.homebanking.models.TransactionType;

import java.time.LocalDateTime;

public class TransactionDto {
    private int id;
    private TransactionType type;
    private double amount;
    private String description;
    private LocalDateTime date;

    public TransactionDto() {
    }

    public TransactionDto(int id, TransactionType type, double amount, String description, LocalDateTime date) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
