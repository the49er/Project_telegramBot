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
import java.util.stream.Collectors;

public class NBUCurrencyService implements CurrencyService {

    @Override
    public Map<String, Double> getRate(Currency currency) {

        //take json from file
        String takeJsonFromFile = Utilities.writeFromJsonFile(CurrencyJsonUpdate.getABSOLUTE_PATH_NBU());

        //Convert json => Java Object
        Type typeToken = TypeToken
                .getParameterized(List.class, CurrencyItemNBU.class)
                .getType();
        List<CurrencyItemNBU> currencyItemsNBU = new Gson().fromJson(takeJsonFromFile, typeToken);

        //Find currency
        double currencyRate = currencyItemsNBU.stream()
                .filter(it -> it.getCc() == currency)
                .map(CurrencyItemNBU::getRate)
                .collect(Collectors.toList()).get(0);

        Map<String, Double> rate = new HashMap<>();
        rate.put("rate" + currency, currencyRate);

        return rate;
    }

    @Data
    public static class CurrencyItemNBU {
        private int r030;
        private String txt;
        private float rate;
        private Currency cc;
        private String exchangeDate;
    }

}
