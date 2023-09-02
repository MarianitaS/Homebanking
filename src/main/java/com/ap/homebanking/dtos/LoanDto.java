package com.ap.homebanking.dtos;

import com.ap.homebanking.models.Loan;

import javax.persistence.ElementCollection;
import java.util.List;

public class LoanDto {

    private long id;
    private String name;
    private double maxAmount;
    @ElementCollection
    private List<Integer> payments;

    public LoanDto() {
    }

    public LoanDto(Loan loan) {
        this.id = loan.getId();
        this.name = loan.getName();
        this.maxAmount = loan.getMaxAmount();
        this.payments = loan.getPayments();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public List<Integer> getPayments() {
        return payments;
    }





}
