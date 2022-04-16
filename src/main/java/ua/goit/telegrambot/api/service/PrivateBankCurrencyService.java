package ua.goit.telegrambot.api.service;

import lombok.Data;
import ua.goit.telegrambot.api.dto.Currency;

import java.util.HashMap;

public class PrivateBankCurrencyService implements CurrencyService{

    @Override
    public HashMap<String, Double> getRate() {
        return null;
    }

    @Data
    public static class CurrencyItemPrivat {
        private Currency ccy;
        private Currency base_ccy;
        private float buy;
        private float sale;
    }

}
