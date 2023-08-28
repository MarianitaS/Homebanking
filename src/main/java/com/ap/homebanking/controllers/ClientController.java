package com.ap.homebanking.controllers;

import com.ap.homebanking.dtos.ClientDto;
import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.repositories.AccountRepository;
import com.ap.homebanking.repositories.ClientRepositiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientRepositiry clientRepositiry;
    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping("/clients")
    public List<ClientDto> getClients(){
        return clientRepositiry.findAll().stream().map(client -> new ClientDto(client)).collect(toList());
    }
    @RequestMapping("/clients/current")
    public ClientDto getCurrent(Authentication authentication) {
        Client client = clientRepositiry.findByEmail(authentication.getName());
        ClientDto current = new ClientDto(client);
        return current;
    }

    @RequestMapping("/clients/{id}")
    public ClientDto getClient(@PathVariable Long id){
        return clientRepositiry.findById(id).map(client -> new ClientDto(client)).orElse(null);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    @RequestMapping(path = "/clients", method = RequestMethod.POST)
    public ResponseEntity<Object> register(
            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password) {

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        if (clientRepositiry.findByEmail(email) !=  null) {
            return new ResponseEntity<>("Email already in use", HttpStatus.FORBIDDEN);
        }

        Account account = new Account(
                "VIN-" + getRandomNumber(11111111, 9999999),
                LocalDate.now(),
                0
        );


        clientRepositiry.save(new Client(firstName, lastName, email, passwordEncoder.encode(password))).addAccount(account);
        accountRepository.save(account);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }



    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }





}
