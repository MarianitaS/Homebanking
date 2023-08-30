package com.ap.homebanking.controllers;


import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.models.Transaction;
import com.ap.homebanking.models.TransactionType;
import com.ap.homebanking.repositories.AccountRepository;
import com.ap.homebanking.repositories.ClientRepositiry;
import com.ap.homebanking.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    ClientRepositiry clientRepositiry;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @Transactional
    @RequestMapping(path = "/transactions", method = RequestMethod.POST)
    public ResponseEntity<Object> transactions( Authentication authentication,
            @RequestParam double amount, @RequestParam String description,
            @RequestParam String fromAccountNumber, @RequestParam String toAccountNumber) {

        Client current = getCurrent(authentication);

        Set<Account> accAut = current.getAccounts();

        Account accountOr = accountRepository.findByNumber(fromAccountNumber);
        Account accDest = accountRepository.findByNumber(toAccountNumber);

        if (description.isEmpty() || amount <=0 ){
            return new ResponseEntity<>("amount or description they are empty", HttpStatus.FORBIDDEN);
        }
        if (fromAccountNumber.isEmpty() || toAccountNumber.isEmpty()) {
            return new ResponseEntity<>("the numbers of account are empty", HttpStatus.FORBIDDEN);
        }
        if (accAut == null){
            return new ResponseEntity<>("unauthenticated user", HttpStatus.FORBIDDEN);
        }
        if (accDest == null) {
            return new ResponseEntity<>("the destination account does not exists", HttpStatus.FORBIDDEN);
        }
        if (accAut.stream().noneMatch(account -> account.getNumber().equals(fromAccountNumber))){
            return new ResponseEntity<>("the source account does not belongs to the authenticated client", HttpStatus.FORBIDDEN);
        }
        if (accountOr.getBalance() < amount ){
            return new ResponseEntity<>("has no funds", HttpStatus.FORBIDDEN);
        }
        if (Objects.equals(toAccountNumber, fromAccountNumber)) {
            return new ResponseEntity<>("the source account is same as destiny", HttpStatus.FORBIDDEN);
        }

        double balanceOr = accountOr.getBalance();
        double balanceDe = accDest.getBalance();

        accountOr.setBalance((balanceOr-amount));
        accDest.setBalance((balanceDe+amount));

        Transaction debit = new Transaction(
                TransactionType.DEBIT,
                amount,
                description,
                LocalDateTime.now()
                );

        Transaction credit = new Transaction(
                TransactionType.CREDIT,
                amount,
                description,
                LocalDateTime.now()
        );



        accountOr.addTransactions(debit);
        accDest.addTransactions(credit);

        transactionRepository.save(debit);
        transactionRepository.save(credit);

        accountRepository.save(accountOr);
        accountRepository.save(accDest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    public Client getCurrent(Authentication authentication) {
        return clientRepositiry.findByEmail(authentication.getName());
    }
}