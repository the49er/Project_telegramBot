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

public class PrivateBankCurrencyService implements CurrencyService {
    public static final String URL = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";

    @Override
    public List<Double> getRate(Currency currency) {

        //Get JSON
        String json = Utilities.getAPIRequest(URL);

        //Convert json => Java Object
        Type typeToken = TypeToken
                .getParameterized(List.class, CurrencyItemPrivat.class)
                .getType();
        List<CurrencyItemPrivat> currencyItemPrivats = new Gson().fromJson(json, typeToken);

        //Find currency
        double privatBuy = currencyItemPrivats.stream()
                .filter(it -> it.getCcy() == currency)
                .filter(it -> it.getBase_ccy() == Currency.UAH)
                .map(CurrencyItemPrivat::getBuy)
                .collect(Collectors.toList()).get(0);

        double privatSele = currencyItemPrivats.stream()
                .filter(it -> it.getCcy() == currency)
                .filter(it -> it.getBase_ccy() == Currency.UAH)
                .map(CurrencyItemPrivat::getSale)
                .collect(Collectors.toList()).get(0);

        List<Double> sellBuyRate = new ArrayList<>();
        sellBuyRate.add(privatBuy);
        sellBuyRate.add(privatSele);

        return sellBuyRate;
    }

    @Data
    public static class CurrencyItemPrivat {
        private Currency ccy;
        private Currency base_ccy;
        private float buy;
        private float sale;
    }

}
