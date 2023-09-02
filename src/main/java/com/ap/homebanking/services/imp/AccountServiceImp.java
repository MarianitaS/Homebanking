package com.ap.homebanking.services.imp;

import com.ap.homebanking.dtos.AccountDto;
import com.ap.homebanking.models.Account;
import com.ap.homebanking.repositories.AccountRepository;
import com.ap.homebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<AccountDto> getAccountsDto() {
        return accountRepository.findAll().stream().map(account -> new AccountDto(account)).collect(toList());
    }
    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }
    @Override
    public void save(Account account) {
    accountRepository.save(account);
    }
    @Override
    public AccountDto getAccount(Long id) {
        return new AccountDto(this.findById(id));
    }
    @Override
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    @Override
    public String getRandomAccount(){
        return   "VIN-" + this.getRandomNumber(11111111, 99999999);
    }

    @Override
    public Account findByNumber(String number){
       return accountRepository.findByNumber(number);
    }


}


