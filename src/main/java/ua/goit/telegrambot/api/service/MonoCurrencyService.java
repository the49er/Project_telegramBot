package ua.goit.telegrambot.api.service;

import lombok.Data;

import java.util.HashMap;


public class MonoCurrencyService implements CurrencyService {


    @Override
    public HashMap<String, Double> getRate() {
        return null;
    }

    @Data
    public static class CurrencyItemMono {
        private int currencyCodeA;
        private int currencyCodeB;
        private int date;
        private float rateBuy;
        private float rateSell;
        private float rateCross;
    }

}
