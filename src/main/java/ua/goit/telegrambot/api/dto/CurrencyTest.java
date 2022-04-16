package ua.goit.telegrambot.api.dto;

import ua.goit.telegrambot.api.dto.threads.MonoBankApiRate;
import ua.goit.telegrambot.api.dto.threads.Rates;

import java.util.Arrays;

public class CurrencyTest {
    public static void main(String[] args) {
       int request = 840;

       MonoBankApi monoBankApi = new MonoBankApi();
        System.out.println(monoBankApi.getCurrencyBuyRatesByUserRequest(request));
        System.out.println(monoBankApi.getCurrencySellRatesByUserRequest(request));




    }
}
