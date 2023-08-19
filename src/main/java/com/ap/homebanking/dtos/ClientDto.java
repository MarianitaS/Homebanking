package com.ap.homebanking.dtos;

import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Card;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.models.ClientLoan;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class ClientDto {

    private long id;
    private String firstName;
    private String   lastName;
    private String    email;
    private Set<Account> accounts;
    private Set<ClientLoan> clientLoans;
    private Set<Card> cards;
    private String password;


    public ClientDto() {
    }

    public ClientDto(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.accounts = client.getAccounts();
        this.clientLoans = client.getClientLoans();
        this.cards = client.getCards();
        this.password = client.getPassword();

    }

    public long getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    @JsonIgnore
    public String getPassword() {
        return password;
    }
    public Set<AccountDto> getAccounts() {
        return accounts.stream().map(account -> new AccountDto(account)).collect(toSet());
    }
    public Set<ClientLoanDto> getLoans() {
        return clientLoans.stream().map(loan -> new ClientLoanDto(loan)).collect(toSet());
    }
    public Set<CardDto> getCards() {
        return cards.stream().map(cards -> new CardDto(cards)).collect(toSet());
    }

}
