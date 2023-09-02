package com.ap.homebanking.services.imp;

import com.ap.homebanking.models.Card;
import com.ap.homebanking.repositories.CardRepository;
import com.ap.homebanking.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImp implements CardService {

    @Autowired
    CardRepository cardRepository;

    @Override
    public void save(Card card) {
        cardRepository.save(card);
    }

    @Override
    public int getRandomNumber(int  min, int max) {
        return (int) ((Math.random() * (min - max)) + min);
    }

    @Override
    public String getRandomCardNumber() {
         return this.getRandomNumber(1111, 9999)+"-"+this.getRandomNumber(1111, 9999)+"-"+this.getRandomNumber(1111, 9999)+"-"+this.getRandomNumber(1111, 9999);
    }
    @Override
    public int getRandomCvv() {
        return this.getRandomNumber(111, 999);
    }

}
