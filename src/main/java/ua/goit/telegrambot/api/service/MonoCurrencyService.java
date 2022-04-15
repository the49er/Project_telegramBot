package ua.goit.telegrambot.api.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import ua.goit.telegrambot.api.dto.Currency;
import ua.goit.telegrambot.utils.Utilities;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MonoCurrencyService implements CurrencyService {
    public static final String URL = "https://api.monobank.ua/bank/currency";


    @Override
    public List<Double> getRate(Currency currency) {

        //Get JSON
        String json = Utilities.getAPIRequest(URL);

        //replace for enum Currency
        String replaceJson = json
                .replace(":840", ":USD")
                .replace(":978", ":EUR")
                .replace(":980", ":UAH")
                .replace(":826", ":GBP");

        //Convert json => Java Object
        Type typeToken = TypeToken
                .getParameterized(List.class, CurrencyItemMono.class)
                .getType();
        List<CurrencyItemMono> currencyItemMono = new Gson().fromJson(replaceJson, typeToken);

        //Find currency mono have delay ~ 5 min, we take all in one
        //EUR
        double monoBuyEUR = currencyItemMono.stream()
                .filter(it -> it.getCurrencyCodeA() == Currency.EUR)
                .filter(it -> it.getCurrencyCodeB() == Currency.UAH)
                .map(CurrencyItemMono::getRateBuy)
                .collect(Collectors.toList()).get(0);

        double monoSaleEUR = currencyItemMono.stream()
                .filter(it -> it.getCurrencyCodeA() == Currency.EUR)
                .filter(it -> it.getCurrencyCodeB() == Currency.UAH)
                .map(CurrencyItemMono::getRateSell)
                .collect(Collectors.toList()).get(0);

        //USD
        double monoBuyUSD = currencyItemMono.stream()
                .filter(it -> it.getCurrencyCodeA() == Currency.USD)
                .filter(it -> it.getCurrencyCodeB() == Currency.UAH)
                .map(CurrencyItemMono::getRateBuy)
                .collect(Collectors.toList()).get(0);

        double monoSaleUSD = currencyItemMono.stream()
                .filter(it -> it.getCurrencyCodeA() == Currency.USD)
                .filter(it -> it.getCurrencyCodeB() == Currency.UAH)
                .map(CurrencyItemMono::getRateSell)
                .collect(Collectors.toList()).get(0);
        //GBP
        double monoCrossCurseGBP = currencyItemMono.stream()
                .filter(it -> it.getCurrencyCodeA() == Currency.GBP)
                .filter(it -> it.getCurrencyCodeB() == Currency.UAH)
                .map(CurrencyItemMono::getRateCross)
                .collect(Collectors.toList()).get(0);

        List<Double> sellBuyRate = new ArrayList<>();
        sellBuyRate.add(monoBuyUSD);
        sellBuyRate.add(monoSaleUSD);
        sellBuyRate.add(monoBuyEUR);
        sellBuyRate.add(monoSaleEUR);
        sellBuyRate.add(monoCrossCurseGBP);

        return sellBuyRate;
    }

    @Data
    public static class CurrencyItemMono {
        private Currency currencyCodeA;
        private Currency currencyCodeB;
        private int date;
        private float rateBuy;
        private float rateSell;
        private float rateCross;
    }

}
