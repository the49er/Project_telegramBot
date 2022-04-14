package ua.goit.telegrambot.api.currency;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import ua.goit.telegrambot.api.currency.dto.Currency;
import ua.goit.telegrambot.util.Utilities;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class NBUCurrencyService implements CurrencyService {
    public static final String URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    @Override
    public List<Double> getRate(Currency currency) {

        //Get JSON
        String json = Utilities.getAPIRequest(URL);

        //Convert json => Java Object
        Type typeToken = TypeToken
                .getParameterized(List.class, CurrencyItemNBU.class)
                .getType();
        List<CurrencyItemNBU> currencyItemsNBU = new Gson().fromJson(json, typeToken);

        //Find currency
        Float currencyRate = currencyItemsNBU.stream()
                .filter(it -> it.getCc() == currency)
                .map(CurrencyItemNBU::getRate)
                .findFirst()
                .orElseThrow();

        List<Double> rate = new ArrayList<>();
        rate.add((double) currencyRate);

        return rate;
    }

    @Data
    public static class CurrencyItemNBU {
        private int r030;
        private String txt;
        private float rate;
        private Currency cc;
        private String exchangedate;
    }

}
