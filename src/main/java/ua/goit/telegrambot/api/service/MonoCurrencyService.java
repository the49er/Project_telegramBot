package ua.goit.telegrambot.api.service;

import lombok.Data;
import ua.goit.telegrambot.api.CurrencyJsonUpdate;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MonoCurrencyService extends ApiService {

    public List<CurrencyItemMono> readFromFileJMonoBank() throws FileNotFoundException {

        CurrencyItemMono[] currencyMonoBank = gson
                .fromJson(new FileReader(CurrencyJsonUpdate.getABSOLUTE_PATH_MONO()), CurrencyItemMono[].class);

        List<CurrencyItemMono> currencyMonoBankList = new ArrayList<>();
        for (CurrencyItemMono monoBank : currencyMonoBank) {
            if (monoBank.getCurrencyCodeA() == 840 || monoBank.getCurrencyCodeA() == 978 || monoBank.getCurrencyCodeA() == 826) {
                currencyMonoBankList.add(monoBank);
            }
        }
        return currencyMonoBankList;

    }

    @Data
    public static class CurrencyItemMono {
        private int currencyCodeA;
        private int currencyCodeB;
        private int date;
        private float rateBuy;
        private float rateSell;
        private float rateCross;
    }

}
