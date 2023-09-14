package com.ap.homebanking.controllers;

import com.ap.homebanking.dtos.ClientDto;
import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.services.AccountService;
import com.ap.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/clients")
    public List<ClientDto> getClients(){
        return clientService.getClientsDto();
    }
    @GetMapping("/clients/current")
    public ClientDto getCurrent(Authentication authentication) {
        return clientService.getCurrent(authentication.getName());
    }
    @GetMapping("/clients/{id}")
    public Object getClient(@PathVariable Long id, Authentication authentication){

        if (clientService.findByEmail(authentication.getName()).equals(clientService.findById(id))) {
            return clientService.getClient(id);
        }
        return HttpStatus.FORBIDDEN;
    }
    @PostMapping(path = "/clients")
    public ResponseEntity<Object> register(
            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password) {

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        if (clientService.findByEmail(email) !=  null) {
            return new ResponseEntity<>("Email already in use", HttpStatus.FORBIDDEN);
        }

        Account account = new Account(
                accountService.getRandomAccount(),
                LocalDate.now(),
                0
        );

        Client client = new Client(
                firstName,
                lastName,
                email,
                passwordEncoder.encode(password));

        clientService.save(client);
        client.addAccount(account);
        accountService.save(account);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}