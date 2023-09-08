package com.ap.homebanking.services;

import com.ap.homebanking.dtos.LoanDto;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.models.Loan;

import java.util.List;

public interface LoanService {

    List<LoanDto> getLoansDto();
    Loan findById(Long id);
    void save(Loan loan);
}
