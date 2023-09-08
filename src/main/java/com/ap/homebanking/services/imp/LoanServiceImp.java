package com.ap.homebanking.services.imp;

import com.ap.homebanking.dtos.LoanDto;
import com.ap.homebanking.models.Loan;
import com.ap.homebanking.repositories.LoanRepository;
import com.ap.homebanking.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class LoanServiceImp implements LoanService {

@Autowired
    private LoanRepository loanRepository;

    @Override
    public List<LoanDto> getLoansDto() {
        return loanRepository.findAll().stream().map(loan -> new LoanDto(loan)).collect(toList());
    }

    @Override
    public Loan findById(Long id) {
        return loanRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Loan loan) {
        loanRepository.save(loan);
    }

}
