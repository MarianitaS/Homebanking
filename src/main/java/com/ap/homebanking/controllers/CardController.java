package com.ap.homebanking.controllers;

import com.ap.homebanking.models.*;
import com.ap.homebanking.repositories.CardRepository;
import com.ap.homebanking.repositories.ClientRepositiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    CardRepository cardRepository;
    @Autowired
    private ClientRepositiry clientRepositiry;

    @RequestMapping(path = "/clients/current/cards", method = RequestMethod.POST)
    public ResponseEntity<Object> register( Authentication authentication,
    @RequestParam CardType cardType, @RequestParam CardColor cardColor) {

         Card cardNew = new Card(
                cardType,
                cardColor,
                getRandomNumber(1111, 9999)+"-"+getRandomNumber(1111, 9999)+"-"+getRandomNumber(1111, 9999)+"-"+getRandomNumber(1111, 9999),
                getRandomNumber(1, 999),
                LocalDate.now().plusYears(5),
                LocalDate.now()
        );

        Client current = getCurrent(authentication);
        Set<Card> cards = current.getCards();

        long type = cards.stream().filter(card -> card.getType() == cardType).count();

        if (type > 2) {
            return new ResponseEntity<>("403 FORBIDDEN",HttpStatus.FORBIDDEN);
        }else {

            current.addCards(cardNew);
            cardRepository.save(cardNew);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    public Client getCurrent(Authentication authentication) {
        return clientRepositiry.findByEmail(authentication.getName());
    }
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
