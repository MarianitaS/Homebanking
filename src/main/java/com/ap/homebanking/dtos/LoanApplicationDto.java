package com.ap.homebanking.dtos;

import javax.persistence.ElementCollection;
import java.util.List;

public class LoanApplicationDto {

    private Long loanId;
    private double amount;
    private int payments;
    private String toAccountNumber;

    public LoanApplicationDto() {
    }

    public Long getloanId() {
        return loanId;
    }

    public double getAmount() {
        return amount;
    }

    public int getPayments() {
        return payments;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }
}
