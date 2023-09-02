package com.ap.homebanking.controllers;

import com.ap.homebanking.models.*;
import com.ap.homebanking.services.CardService;
import com.ap.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    private CardService cardService;
    @Autowired
    private ClientService clientService;

    @RequestMapping(path = "/clients/current/cards", method = RequestMethod.POST)
    public ResponseEntity<Object> register( Authentication authentication,
    @RequestParam CardType cardType, @RequestParam CardColor cardColor) {

         Card cardNew = new Card(
                cardType,
                cardColor,
                cardService.getRandomCardNumber(),
                cardService.getRandomCvv(),
                LocalDate.now().plusYears(5),
                LocalDate.now()
        );

        Client current = clientService.findByEmail(authentication.getName());
        Set<Card> cards = current.getCards();

        long typeCards = cards.stream().filter(card -> card.getType() == cardType).count();

        if (typeCards > 2) {
            return new ResponseEntity<>("403 FORBIDDEN",HttpStatus.FORBIDDEN);
        }else {

            current.addCards(cardNew);
            cardService.save(cardNew);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
