package com.ap.homebanking.controllers;

import com.ap.homebanking.dtos.AccountDto;
import com.ap.homebanking.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping("/accounts")
    public List<AccountDto> getAccounts(){
        return accountRepository.findAll().stream().map(AccountDto::new).collect(toList());
    }
    @RequestMapping("/accounts/{id}")
    public AccountDto getAccount(@PathVariable Integer id){
        return accountRepository.findById(id).map(AccountDto::new).orElse(null);
    }





}
