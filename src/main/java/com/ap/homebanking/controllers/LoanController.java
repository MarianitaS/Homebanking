package com.ap.homebanking.controllers;

import com.ap.homebanking.dtos.LoanApplicationDto;
import com.ap.homebanking.dtos.LoanDto;
import com.ap.homebanking.models.*;
import com.ap.homebanking.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class LoanController {

    @Autowired
    private LoanService loanService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientLoanService clientLoanService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AccountService accountService;


    @RequestMapping("/loans")
    public List<LoanDto> getLoans(){
        return loanService.getLoansDto();
    }
    @Transactional
    @RequestMapping(path = "/loans", method = RequestMethod.POST)
    public ResponseEntity<String> addLoan (@RequestBody LoanApplicationDto loanApplicationDto, Authentication authentication) {

        Set<Account> currAcc = clientService.getCurrentAcc(authentication.getName());

        if (loanApplicationDto.getAmount() <= 0 || loanApplicationDto.getloanId().toString().isBlank() ||
                loanApplicationDto.getPayments() <=0 || loanApplicationDto.getToAccountNumber().isBlank()){
            return new ResponseEntity<>("Missing data",HttpStatus.FORBIDDEN);
        }
        if (loanService.findById(loanApplicationDto.getloanId())==null){
            return new ResponseEntity<>("the loan does not exist",HttpStatus.FORBIDDEN);
        }
        if (loanApplicationDto.getAmount()>loanService.findById(loanApplicationDto.getloanId()).getMaxAmount()){
            return new ResponseEntity<>("the amount exceeds the maximum available",HttpStatus.FORBIDDEN);
        }
        if (!loanService.findById(loanApplicationDto.getloanId()).getPayments().contains(loanApplicationDto.getPayments())) {
            return new ResponseEntity<>("the payments do not correspond to the loan",HttpStatus.FORBIDDEN);
        }
        if (accountService.findByNumber(loanApplicationDto.getToAccountNumber()) == null){
            return new ResponseEntity<>("destination account does not exist",HttpStatus.FORBIDDEN);
        }
        if (currAcc.stream().noneMatch(account -> account.getNumber().equals(loanApplicationDto.getToAccountNumber()))){
            return new ResponseEntity<>("the account does not belong to the client",HttpStatus.FORBIDDEN);
        }

        ClientLoan loanAdd = new ClientLoan ((loanApplicationDto.getAmount()*1.2), loanApplicationDto.getPayments());

        Client current = clientService.findByEmail(authentication.getName());
        Account accLoan = accountService.findByNumber(loanApplicationDto.getToAccountNumber());
        Loan loan = loanService.findById(loanApplicationDto.getloanId());

        Set<Account> accounts = current.getAccounts();

        Transaction credit = new Transaction(
                TransactionType.CREDIT,
                loanApplicationDto.getAmount(),
                loan.getName() + " - loan approved",
                LocalDateTime.now()
        );

        accLoan.addTransactions(credit);
        accLoan.setBalance(accLoan.getBalance()+loanApplicationDto.getAmount());

        loan.addClientLoans(loanAdd);

        current.addClientLoans(loanAdd);

        accountService.save(accLoan);
        clientLoanService.save(loanAdd);
        clientService.save(current);
        transactionService.save(credit);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}