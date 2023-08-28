package com.ap.homebanking.controllers;

import com.ap.homebanking.dtos.AccountDto;
import com.ap.homebanking.dtos.ClientDto;
import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.repositories.AccountRepository;
import com.ap.homebanking.repositories.ClientRepositiry;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepositiry clientRepositiry;
    @RequestMapping("/accounts")
    public List<AccountDto> getAccounts(){
        return accountRepository.findAll().stream().map(AccountDto::new).collect(toList());
    }
    @RequestMapping("/accounts/{id}")
    public AccountDto getAccount(@PathVariable Long id){
        return accountRepository.findById(id).map(AccountDto::new).orElse(null);
    }
    @RequestMapping(path = "/clients/current/accounts", method = RequestMethod.POST)
    public ResponseEntity<Object> create(Authentication authentication) {


        Account account = new Account(
                "VIN-" + getRandomNumber(11111111, 9999999),
                today,
                0
        );

        Client current = getCurrent(authentication);
        Set<Account> account2 = current.getAccounts();
        if( account2.size() > 2 )  {
            return new ResponseEntity<>("403 FORBIDDEN",HttpStatus.FORBIDDEN);
        } else {
            current.addAccount(account);
            accountRepository.save(account);
            return new ResponseEntity<>(HttpStatus.CREATED);

        }

    }

    public Client getCurrent(Authentication authentication) {
       return clientRepositiry.findByEmail(authentication.getName());
    }
    LocalDate today = LocalDate.now();
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }






}
