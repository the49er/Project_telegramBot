package ua.goit.telegrambot.api;

import lombok.SneakyThrows; // need to discuss
import ua.goit.telegrambot.api.dto.Currency;
import ua.goit.telegrambot.api.service.MonoCurrencyService;
import ua.goit.telegrambot.api.service.NBUCurrencyService;
import ua.goit.telegrambot.api.service.PrivateBankCurrencyService;
import ua.goit.telegrambot.api.utils.Utilities;

public class GetRatesTest {
    @SneakyThrows
    public static void main(String[] args) {

        NBUCurrencyService nbuCurrencyService = new NBUCurrencyService();
        PrivateBankCurrencyService privateBankCurrencyService = new PrivateBankCurrencyService();
        MonoCurrencyService monoCurrencyService = new MonoCurrencyService();

        //example of a currency request
        System.out.println("\"NBU\" = " + "NBU");
        System.out.println("nbuCurrencyService.getRate(Currency.USD) = " + nbuCurrencyService.getRate(Currency.USD));
        System.out.println("nbuCurrencyService.getRate(Currency.EUR) = " + nbuCurrencyService.getRate(Currency.EUR));
        System.out.println("nbuCurrencyService.getRate(Currency.GBP) = " + nbuCurrencyService.getRate(Currency.GBP));
        System.out.println("\"Privat\" = " + "Privat");
        System.out.println("privateBankCurrencyService.getRate(Currency.USD) = " + privateBankCurrencyService.getRate(Currency.USD));
        System.out.println("privateBankCurrencyService.getRate(Currency.EUR) = " + privateBankCurrencyService.getRate(Currency.EUR));
        System.out.println("\"Mono\" = " + "Mono");
        System.out.println("monoCurrencyService.getRate(Currency.USD) = " + monoCurrencyService.getRate(Currency.USD));
        System.out.println("monoCurrencyService.getRate(Currency.EUR) = " + monoCurrencyService.getRate(Currency.EUR));
        System.out.println("monoCurrencyService.getRate(Currency.GBP) = " + monoCurrencyService.getRate(Currency.GBP));




    }



}
