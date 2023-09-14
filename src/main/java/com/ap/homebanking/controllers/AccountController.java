package com.ap.homebanking.controllers;

import com.ap.homebanking.dtos.AccountDto;
import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.services.AccountService;
import com.ap.homebanking.services.ClientService;
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
    private AccountService accountService;
    @Autowired
    private ClientService clientService;

    @GetMapping("/accounts")
    public List<AccountDto> getAccountsDto(){
        return accountService.getAccountsDto();
    }
    @GetMapping("/accounts/{id}")
    public Object getAccount(@PathVariable Long id, Authentication authentication){

        if (clientService.findByEmail(authentication.getName()).getAccounts().contains(accountService.findById(id)))
        {
            return accountService.getAccount(id);
        }
            return (HttpStatus.FORBIDDEN);
    }
    @GetMapping("/clients/current/accounts")
    public List<AccountDto> getCurrAcc(Authentication authentication) {

      Set<Account> currAcc = clientService.getCurrentAcc(authentication.getName());
      List<AccountDto> accountDtos = currAcc.stream().map(account -> new AccountDto(account)).collect(toList());
          return accountDtos;
    }
    @PostMapping(path = "/clients/current/accounts")
    public ResponseEntity<Object> create(Authentication authentication) {

        Account account = new Account(
                accountService.getRandomAccount(),
                today,
                0
        );

        Client current = clientService.findByEmail(authentication.getName());
        Set<Account> account2 = current.getAccounts();

        if( account2.size() > 2 )  {
            return new ResponseEntity<>("403 FORBIDDEN",HttpStatus.FORBIDDEN);
        } else {

            current.addAccount(account);
            accountService.save(account);
            return new ResponseEntity<>(HttpStatus.CREATED);

        }
    }
    LocalDate today = LocalDate.now();

}

