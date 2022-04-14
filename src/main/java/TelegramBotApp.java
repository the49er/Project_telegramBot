import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ua.goit.telegrambot.api.currency.FileUpdate;
import ua.goit.telegrambot.telegram.TelegramCurrencyBot;

public class TelegramBotApp {
    public static void main(String[] args) {
        //take and update currency exchange
        FileUpdate fileUpdate = new FileUpdate();
        fileUpdate.start();

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramCurrencyBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
