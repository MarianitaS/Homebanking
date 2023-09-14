package com.ap.homebanking.controllers;

import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.models.Transaction;
import com.ap.homebanking.models.TransactionType;
import com.ap.homebanking.services.AccountService;
import com.ap.homebanking.services.ClientService;
import com.ap.homebanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;

    @Transactional
    @PostMapping(path = "/transactions")
    public ResponseEntity<Object> transactions( Authentication authentication,
            @RequestParam double amount, @RequestParam String description,
            @RequestParam String fromAccountNumber, @RequestParam String toAccountNumber) {

        Set<Account> accAut = clientService.getCurrentAcc(authentication.getName());

        Account accOri = accountService.findByNumber(fromAccountNumber);
        Account accDes = accountService.findByNumber(toAccountNumber);

        if (description.isEmpty() || amount <=0 ){
            return new ResponseEntity<>("amount or description they are empty", HttpStatus.FORBIDDEN);
        }
        if (fromAccountNumber.isEmpty() || toAccountNumber.isEmpty()) {
            return new ResponseEntity<>("the numbers of account are empty", HttpStatus.FORBIDDEN);
        }
        if (accAut == null){
            return new ResponseEntity<>("unauthenticated user", HttpStatus.FORBIDDEN);
        }
        if (accDes == null) {
            return new ResponseEntity<>("the destination account does not exists", HttpStatus.FORBIDDEN);
        }
        if (accAut.stream().noneMatch(account -> account.getNumber().equals(fromAccountNumber))){
            return new ResponseEntity<>("the source account does not belongs to the authenticated client", HttpStatus.FORBIDDEN);
        }
        if (accOri.getBalance() < amount ){
            return new ResponseEntity<>("has no funds", HttpStatus.FORBIDDEN);
        }
        if (Objects.equals(toAccountNumber, fromAccountNumber)) {
            return new ResponseEntity<>("the source account is same as destiny", HttpStatus.FORBIDDEN);
        }

        accOri.setBalance((accOri.getBalance()-amount));
        accDes.setBalance((accDes.getBalance()+amount));

        Transaction debit = new Transaction(
                TransactionType.DEBIT,
                -amount,
                description,
                LocalDateTime.now()
                );
        Transaction credit = new Transaction(
                TransactionType.CREDIT,
                amount,
                description,
                LocalDateTime.now()
        );

        accOri.addTransactions(debit);
        accDes.addTransactions(credit);

        transactionService.save(debit);
        transactionService.save(credit);

        accountService.save(accOri);
        accountService.save(accDes);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}