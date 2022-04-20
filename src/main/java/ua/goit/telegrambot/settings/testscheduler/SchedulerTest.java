package ua.goit.telegrambot.settings.testscheduler;


import ua.goit.telegrambot.telegram.TelegramCurrencyBot;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimerTask;

public class SchedulerTest extends TimerTask {
    UserForSchedulerTest userMe = new UserForSchedulerTest();
    Calendar calendar = GregorianCalendar.getInstance();

    @Override
    public void run() {
        checkUsers(userMe);
    }

    public void checkUsers(UserForSchedulerTest userMe) {
        if (userMe.notificationTime == calendar.get(Calendar.HOUR_OF_DAY)) {
            TelegramCurrencyBot bot = new TelegramCurrencyBot();
            bot.sendNotification(userMe.getId());
        }
    }
}
