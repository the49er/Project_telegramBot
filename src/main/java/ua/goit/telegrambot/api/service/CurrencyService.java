package ua.goit.telegrambot.api.service;

import ua.goit.telegrambot.api.dto.Currency;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public interface CurrencyService {
    Map<String, BigDecimal> getRate(Currency currency) throws IOException;
}
