package ua.goit.telegrambot.api.service;

import lombok.Data;
import ua.goit.telegrambot.api.CurrencyJsonUpdate;
import ua.goit.telegrambot.api.dto.Currency;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class PrivateBankCurrencyService extends ApiService {

    public List<CurrencyItemPrivat> readFromFileJPrivat() throws FileNotFoundException {

        CurrencyItemPrivat[] currencyPrivatBanks = gson
                .fromJson(new FileReader(CurrencyJsonUpdate.getABSOLUTE_PATH_PRIVAT()), CurrencyItemPrivat[].class);

        List<CurrencyItemPrivat> currencyPrivatBankList = new ArrayList<>();

        for (CurrencyItemPrivat currencyPrivatBank : currencyPrivatBanks) {
            if (currencyPrivatBank.getCcy().equals("USD") || currencyPrivatBank.getCcy().equals("EUR")) {
                currencyPrivatBankList.add(currencyPrivatBank);
            }
        }

        return currencyPrivatBankList;
    }

    @Data
    public static class CurrencyItemPrivat {
        private Currency ccy;
        private Currency base_ccy;
        private float buy;
        private float sale;
    }

}
