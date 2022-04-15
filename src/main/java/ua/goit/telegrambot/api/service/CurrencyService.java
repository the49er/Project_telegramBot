package ua.goit.telegrambot.api.service;

import ua.goit.telegrambot.api.dto.Currency;

import java.util.List;

public interface CurrencyService {
    List<Double> getRate(Currency currency);
}
