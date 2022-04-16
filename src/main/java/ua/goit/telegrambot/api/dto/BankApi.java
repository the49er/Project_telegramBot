package ua.goit.telegrambot.api.dto;

import com.google.gson.Gson;

import java.net.http.HttpClient;

public abstract class BankApi {
    public static final HttpClient CLIENT = HttpClient.newHttpClient();
    public static final Gson GSON = new Gson();

    public abstract void getRatesAndSaveToJsonFile();
    public abstract double getCurrencySellRatesByUserRequest (int currencyA, int currencyB, int roundTheNumber);
    public abstract double getCurrencyBuyRatesByUserRequest (int currencyA, int currencyB, int roundTheNumber);


}
