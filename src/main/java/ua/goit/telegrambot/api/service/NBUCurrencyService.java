package ua.goit.telegrambot.api.service;

import lombok.Data;
import ua.goit.telegrambot.api.CurrencyJsonUpdate;
import ua.goit.telegrambot.api.dto.Currency;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NBUCurrencyService extends ApiService {

    public List<CurrencyItemNBU> getCurrency() throws IOException {
        CurrencyItemNBU[] currencyNbu = gson
                .fromJson(new FileReader(CurrencyJsonUpdate.getABSOLUTE_PATH_NBU()), CurrencyItemNBU[].class);

        List<CurrencyItemNBU> currencyNbuList = new ArrayList<>();

        for (CurrencyItemNBU nbu : currencyNbu) {
            if (nbu.getR030() == 840 || nbu.getR030() == 978) {
                currencyNbuList.add(nbu);
            }
        }

        return currencyNbuList;
    }

    @Data
    public static class CurrencyItemNBU {
        private int r030;
        private float rate;
        private Currency cc;

    }

}
