package com.ap.homebanking.dtos;


import com.ap.homebanking.models.ClientLoan;


public class ClientLoanDto {

    private long id;
    private long loanId;
    private String name;
    private double amount;
    private int payments;

    public ClientLoanDto() {
    }

    public ClientLoanDto(ClientLoan clientLoan) {
        this.id = clientLoan.getId();
        loanId = clientLoan.getLoan().getId();
        name = clientLoan.getLoan().getName();
        this.amount = clientLoan.getAmount();
        this.payments = clientLoan.getPayments();
    }

    public long getId() {
        return id;
    }

    public long getLoanId() {
        return loanId;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public int getPayments() {
        return payments;
    }
}