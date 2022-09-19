package ua.goit.telegrambot.api.service;

import ua.goit.telegrambot.api.dto.Currency;

import java.io.IOException;
import java.util.Map;

public interface CurrencyService {
    Map<String, Double> getRate(Currency currency);
}
