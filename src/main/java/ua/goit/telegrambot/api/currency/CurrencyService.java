package ua.goit.telegrambot.api.currency;

import ua.goit.telegrambot.api.currency.dto.Currency;

import java.util.List;

public interface CurrencyService {
    List<Double> getRate(Currency currency);
}
