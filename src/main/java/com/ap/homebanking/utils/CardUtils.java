package com.ap.homebanking.utils;

public final class CardUtils {

    public static String getCardNumber() {
        String cardNumber = (int) ((Math.random()*(1000-9999))+1000)
                +"-"+(int) ((Math.random()*(1000-9999))+1000)
                +"-"+(int) ((Math.random()*(1000-9999))+1000)
                +"-"+(int) ((Math.random()*(1000-9999))+1000);
        return cardNumber;
    }

public static int getCVV(){
        int cvvNumber = (int) ((Math.random()*(1000-9999))+1000);
        return cvvNumber;
}

    private CardUtils() {
    }


}
