import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ua.goit.telegrambot.api.CurrencyJsonUpdate;
import ua.goit.telegrambot.api.dto.Currency;
import ua.goit.telegrambot.api.service.NBUCurrencyService;
import ua.goit.telegrambot.api.service.PrivateBankCurrencyService;
import ua.goit.telegrambot.telegram.TelegramCurrencyBot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class TelegramBotApp {
    public static void main(String[] args) throws FileNotFoundException {
        //take and update currency exchange
        CurrencyJsonUpdate fileCurrencyJsonUpdate = new CurrencyJsonUpdate();
        fileCurrencyJsonUpdate.run();

        PrivateBankCurrencyService privateBankCurrencyService = new PrivateBankCurrencyService();
        PrivateBankCurrencyService.CurrencyItemPrivat privatUSD = privateBankCurrencyService.readFromFileJPrivat().get(0);
        PrivateBankCurrencyService.CurrencyItemPrivat privatEUR = privateBankCurrencyService.readFromFileJPrivat().get(1);
        System.out.println("privatUSD = " + privatUSD);
        System.out.println("privatEUR = " + privatEUR);

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramCurrencyBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
