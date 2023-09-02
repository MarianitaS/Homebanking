package com.ap.homebanking.services;


import com.ap.homebanking.models.Card;

public interface CardService {

    void save (Card card);

    int getRandomNumber(int min, int max);

    String getRandomCardNumber();

    int getRandomCvv();
}
