import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ua.goit.telegrambot.api.CurrencyJsonUpdate;
import ua.goit.telegrambot.api.dto.Currency;
import ua.goit.telegrambot.api.service.MonoCurrencyService;
import ua.goit.telegrambot.api.service.NBUCurrencyService;
import ua.goit.telegrambot.api.service.PrivateBankCurrencyService;
import ua.goit.telegrambot.telegram.TelegramCurrencyBot;
import ua.goit.telegrambot.utils.Utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class TelegramBotApp {
    public static void main(String[] args) throws IOException {
        //take and update currency exchange
        CurrencyJsonUpdate fileCurrencyJsonUpdate = new CurrencyJsonUpdate();
        fileCurrencyJsonUpdate.run();
        Utilities.wait(5);

        NBUCurrencyService nbuCurrencyService = new NBUCurrencyService();
        System.out.println("\"NBU\" = " + "NBU");
        System.out.println("nbuCurrencyService.getRate(Currency.USD) = " + nbuCurrencyService.getRate(Currency.USD));
        System.out.println("nbuCurrencyService.getRate(Currency.EUR) = " + nbuCurrencyService.getRate(Currency.EUR));
        System.out.println("nbuCurrencyService.getRate(Currency.GBP) = " + nbuCurrencyService.getRate(Currency.GBP));
        System.out.println("\"Privat\" = " + "Privat");
        PrivateBankCurrencyService privateBankCurrencyService = new PrivateBankCurrencyService();
        System.out.println("privateBankCurrencyService.getRate(Currency.USD) = " + privateBankCurrencyService.getRate(Currency.USD));
        System.out.println("privateBankCurrencyService.getRate(Currency.EUR) = " + privateBankCurrencyService.getRate(Currency.EUR));
        System.out.println("\"Mono\" = " + "Mono");
        MonoCurrencyService monoCurrencyService = new MonoCurrencyService();
        System.out.println("monoCurrencyService.getRate(Currency.USD) = " + monoCurrencyService.getRate(Currency.USD));
        System.out.println("monoCurrencyService.getRate(Currency.EUR) = " + monoCurrencyService.getRate(Currency.EUR));
        System.out.println("monoCurrencyService.getRate(Currency.GBP) = " + monoCurrencyService.getRate(Currency.GBP));

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramCurrencyBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
