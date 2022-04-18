package ua.goit.telegrambot.ui;

import ua.goit.telegrambot.api.dto.Currency;

public class PrintCurrencyService {
    public String convert(double rate, Currency currency) {

        String template = "${currency} exchange rate to UAH = ${rate}"; //"USD exchange rate to UAH = {some rate}"

        return template
                .replace("${currency}", currency.name())
                .replace("${rate}", Double.toString(rate)); // should add rounder
    }

}
