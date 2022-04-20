import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ua.goit.telegrambot.api.CurrencyJsonUpdate;
import ua.goit.telegrambot.settings.Scheduler;
import ua.goit.telegrambot.settings.testscheduler.SchedulerTest;
import ua.goit.telegrambot.telegram.TelegramCurrencyBot;

import java.util.Timer;
import java.util.TimerTask;

@Slf4j
public class TelegramBotApp {
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramCurrencyBot());
            log.info("API starts working");

        } catch (TelegramApiRequestException apiRequestException) {
            log.info("Catch: API request exception: more than one instance of the CurrencyBot has launched");

        } catch (TelegramApiException e) {
            log.info("Catch: "+ e.getMessage());

        }

        CurrencyJsonUpdate fileCurrencyJsonUpdate = new CurrencyJsonUpdate();
        fileCurrencyJsonUpdate.run();
        Scheduler scheduler = new Scheduler();
        Thread thread = new Thread(scheduler);
        thread.run();
        log.info("API Threads started");

        //The Block Of Timer Code
        Timer timer = new Timer();
        TimerTask task = new SchedulerTest();
        // scheduling the timer instance
        timer.schedule(task, 1, 1000*60*60);

    }
}
