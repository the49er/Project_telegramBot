package ua.goit.telegrambot.settings;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import ua.goit.telegrambot.telegram.TelegramCurrencyBot;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Slf4j
public class Scheduler implements Runnable {


    @SneakyThrows
    @Override
    public void run() {
        //check time when bot is used first time
        Calendar calendar = new GregorianCalendar();
        TelegramCurrencyBot bot = new TelegramCurrencyBot();

//        if (calendar.get(Calendar.HOUR_OF_DAY) >= 0 && calendar.get(Calendar.HOUR_OF_DAY) < 8){
//            calendar.set(Calendar.HOUR_OF_DAY, 9);
//            calendar.set(Calendar.MINUTE, 0);
//            calendar.set(Calendar.SECOND, 0);
//            Long date = calendar.getTimeInMillis();
//            Long delay = date - System.currentTimeMillis();
//            Thread.sleep(delay);
//        }else if (calendar.get(Calendar.HOUR_OF_DAY) > 18 && calendar.get(Calendar.HOUR_OF_DAY) < 24){
//            calendar.add(Calendar.DAY_OF_MONTH, 1);
//            calendar.set(Calendar.HOUR_OF_DAY, 9);
//            calendar.set(Calendar.MINUTE, 0);
//            calendar.set(Calendar.SECOND, 0);
//            Long date = calendar.getTimeInMillis();
//            Long delay = date - System.currentTimeMillis();
//            Thread.sleep(delay);
//        }

        if (calendar.get(Calendar.HOUR_OF_DAY) >= 0 && calendar.get(Calendar.HOUR_OF_DAY) < 8){
            calendar.set(Calendar.HOUR_OF_DAY, 9);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Long date = calendar.getTimeInMillis();
            Long delay = date - System.currentTimeMillis();
            Thread.sleep(delay);
        }else if (calendar.get(Calendar.HOUR_OF_DAY) > 18 && calendar.get(Calendar.HOUR_OF_DAY) < 24){
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 9);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Long date = calendar.getTimeInMillis();
            Long delay = date - System.currentTimeMillis();
            Thread.sleep(delay);
        }


        UserService service = UserService.getInstance();
        while (true) {
            calendar = new GregorianCalendar();
            int time = calendar.get(Calendar.HOUR_OF_DAY);
            List<Integer> userIds = service.getUsersWithNotficationOnCurrentHour(time);

            for (Integer userId : userIds) {
                bot.sendNotification(Long.valueOf(userId));
                log.info("sent notification");
            }
            if (time >= 9 && time <= 17) {
                Thread.sleep(3600000l);
            } else if (time >= 18) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                calendar.set(Calendar.HOUR_OF_DAY, 9);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                Long date = calendar.getTimeInMillis();
                Long delay = date - System.currentTimeMillis();
                Thread.sleep(delay);
            }
        }
    }
}
