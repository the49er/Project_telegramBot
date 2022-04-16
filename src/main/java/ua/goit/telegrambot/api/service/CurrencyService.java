package ua.goit.telegrambot.api.service;

import java.util.HashMap;

public interface CurrencyService {
    HashMap<String, Double> getRate();
}
