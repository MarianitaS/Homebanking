package com.ap.homebanking.dtos;

import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;

import java.util.Set;

public class ClientDto {

    private int id;

    private String firstName;
    private String   lastName;
    private String    email;
    private Set<Account> accounts;

    public ClientDto() {
    }

    public ClientDto(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.accounts = client.getAccounts();
    }

    public int getId() {
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
    public Set<Account> getAccount() {
        return accounts;
    }


}
