package ua.goit.telegrambot.settings;

import ua.goit.telegrambot.api.dto.Currency;
import ua.goit.telegrambot.api.service.MonoCurrencyService;
import ua.goit.telegrambot.api.service.NBUCurrencyService;
import ua.goit.telegrambot.api.service.PrivateBankCurrencyService;

import java.math.BigDecimal;
import java.util.Map;

public class Tset {
    public static void main(String[] args) {
        NBUCurrencyService nbuCurrencyService = new NBUCurrencyService();
        PrivateBankCurrencyService privateBankCurrencyService = new PrivateBankCurrencyService();
        MonoCurrencyService monoCurrencyService = new MonoCurrencyService();


        Map<String, BigDecimal> rate = monoCurrencyService.getRate(Currency.USD);
        Map<String, BigDecimal> rate1 = monoCurrencyService.getRate(Currency.EUR);
        Map<String, BigDecimal> rate2 = monoCurrencyService.getRate(Currency.GBP);

        System.out.println(rate);
        System.out.println(rate1);
        System.out.println(rate2);
    }
}
