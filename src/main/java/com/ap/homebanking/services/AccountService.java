package com.ap.homebanking.services;

import com.ap.homebanking.dtos.AccountDto;
import com.ap.homebanking.models.Account;


import java.util.List;


public interface AccountService {

    List<AccountDto> getAccountsDto();

    Account findById(Long id);

    AccountDto getAccount (Long id);

    void save (Account account);


    int getRandomNumber(int min, int max);

    String getRandomAccount();

    Account findByNumber(String number);
}
