package ua.goit.telegrambot.api.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import ua.goit.telegrambot.api.CurrencyJsonUpdate;
import ua.goit.telegrambot.api.dto.Currency;
import ua.goit.telegrambot.api.utils.Utilities;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PrivateBankCurrencyService implements CurrencyService {

    @Override
    public Map<String, Double> getRate(Currency currency) {//repetative code

        //take json from file
        String takeJsonFromFile = Utilities.writeFromJsonFile(CurrencyJsonUpdate.getABSOLUTE_PATH_PRIVAT());

        //Convert json => Java Object
        Type typeToken = TypeToken
                .getParameterized(List.class, CurrencyItemPrivat.class)
                .getType();
        List<CurrencyItemPrivat> currencyItemPrivats = new Gson().fromJson(takeJsonFromFile, typeToken);

        //Find currency
        double privatBuy = getCurrency(CurrencyItemPrivat::getBuy, currencyItemPrivats, currency);
        double privatSell = getCurrency(CurrencyItemPrivat::getSale, currencyItemPrivats, currency);

        Map<String, Double> rate = new HashMap<>();
        rate.put("buy" + currency, privatBuy);
        rate.put("sell" + currency, privatSell);

        return rate;
    }

    private static double getCurrency(Function<CurrencyItemPrivat, Float> function, List<CurrencyItemPrivat> currencyItemPrivats,
                                      Currency currency) {
        return currencyItemPrivats.stream()
                .filter(it -> it.getCcy() == currency)
                .map(function)
                .collect(Collectors.toList()).get(0);
    }

    @Data
    public static class CurrencyItemPrivat {
        private Currency ccy;
        private Currency base_ccy;
        private float buy;
        private float sale;
    }

}
