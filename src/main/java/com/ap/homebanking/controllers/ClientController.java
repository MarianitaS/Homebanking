package com.ap.homebanking.controllers;

import com.ap.homebanking.dtos.ClientDto;
import com.ap.homebanking.repositories.ClientRepositiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientRepositiry clientRepositiry;

    @RequestMapping("/clients")
    public List<ClientDto> getClients(){
        return clientRepositiry.findAll().stream().map(ClientDto::new).collect(toList());
    }
    @RequestMapping("/clients/{id}")
    public ClientDto getClient(@PathVariable Integer id){
        return clientRepositiry.findById(id).map(ClientDto::new).orElse(null);
    }


}