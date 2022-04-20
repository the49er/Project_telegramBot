package ua.goit.telegrambot;

import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ua.goit.telegrambot.api.CurrencyJsonUpdate;
import ua.goit.telegrambot.api.dto.Currency;
import ua.goit.telegrambot.api.service.MonoCurrencyService;
import ua.goit.telegrambot.api.service.NBUCurrencyService;
import ua.goit.telegrambot.api.service.PrivateBankCurrencyService;
import ua.goit.telegrambot.utils.Utilities;

import java.io.IOException;


@Slf4j
public class TelegramBotApp {
    public static void main(String[] args) throws IOException {
        NBUCurrencyService nbuCurrencyService = new NBUCurrencyService();
        PrivateBankCurrencyService privateBankCurrencyService = new PrivateBankCurrencyService();
        MonoCurrencyService monoCurrencyService = new MonoCurrencyService();
        //take and update currency exchange
        CurrencyJsonUpdate fileCurrencyJsonUpdate = new CurrencyJsonUpdate();
        fileCurrencyJsonUpdate.run();

        Utilities.wait(3);

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

        /*try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramCurrencyBot());
            log.info("API starts working");

        } catch (TelegramApiRequestException apiRequestException) {
            log.info("Catch: API request exception: more than one instance of the CurrencyBot has launched");

        } catch (TelegramApiException e) {
            log.info("Catch: "+ e.getMessage());

        }*/

    }
}