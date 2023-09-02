package com.ap.homebanking.services;

import com.ap.homebanking.dtos.ClientDto;
import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Set;

public interface ClientService {
    List<ClientDto> getClientsDto();

    Client findById(Long id);

    ClientDto getClient(Long id);

    void save(Client client);

    Client findByEmail(String email);

    ClientDto getCurrent(String email);

    Set<Account> getCurrentAcc(String email);

}
