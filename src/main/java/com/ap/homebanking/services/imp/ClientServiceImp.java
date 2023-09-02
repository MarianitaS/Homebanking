package com.ap.homebanking.services.imp;

import com.ap.homebanking.dtos.AccountDto;
import com.ap.homebanking.dtos.ClientDto;
import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.repositories.ClientRepositiry;
import com.ap.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
public class ClientServiceImp implements ClientService {


    @Autowired
    private ClientRepositiry clientRepositiry;

    @Override
    public List<ClientDto> getClientsDto() {
        return clientRepositiry.findAll().stream().map(client -> new ClientDto(client)).collect(toList());
    }

    @Override
    public Client findById(Long id) {
        return clientRepositiry.findById(id).orElse(null);
    }

    @Override
    public ClientDto getClient(Long id) {
        return new ClientDto(this.findById(id));
    }

    @Override
    public void save(Client client) {
    clientRepositiry.save(client);
    }

    @Override
    public Client findByEmail(String email) {
        return clientRepositiry.findByEmail(email);
    }

    @Override
    public ClientDto getCurrent(String email) {
        return new ClientDto(this.findByEmail(email));
    }

    @Override
    public Set<Account> getCurrentAcc(String email){

        return this.findByEmail(email).getAccounts();
    }
}
