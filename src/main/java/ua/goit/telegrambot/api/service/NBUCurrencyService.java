package ua.goit.telegrambot.api.service;

import lombok.Data;
import ua.goit.telegrambot.api.dto.Currency;

import java.util.HashMap;

public class NBUCurrencyService implements CurrencyService {


    @Override
    public HashMap<String, Double> getRate() {
        return null;
    }

    @Data
    public static class CurrencyItemNBU {
        private int r030;
        private String txt;
        private float rate;
        private Currency cc;
        private String exchangedate;
    }

}
