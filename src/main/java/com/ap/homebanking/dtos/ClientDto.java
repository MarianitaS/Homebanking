package com.ap.homebanking.dtos;

import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Card;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.models.ClientLoan;

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
