package ua.goit.telegrambot.settings;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import ua.goit.telegrambot.telegram.TelegramCurrencyBot;
import java.util.Calendar;
import java.util.Date;
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


        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour <9 || hour > 18) {
            log.info("thread to sleep");
            if(hour>=18) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
            calendar.set(Calendar.HOUR_OF_DAY, 9);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date date1 = calendar.getTime();
            long delay = date1.getTime() - System.currentTimeMillis()+5000;
            Thread.sleep(delay);
        }


        UserService service = UserService.getInstance();

        while (true) {//https://mkyong.com/java/how-to-run-a-task-periodically-in-java/
            log.info("Notifications Thread has started");
            calendar = new GregorianCalendar();
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            List<Long> userIds = service.getUsersWithNotificationOnCurrentHour(hour);

            for (long userId : userIds) {
                bot.sendNotification(userId);
                log.info("Notification has been sent");
            }
            if (hour >= 18){
                calendar.add(Calendar.HOUR_OF_DAY,1);
            }
            if (hour < 8){
                calendar.set(Calendar.HOUR_OF_DAY,9);
                calendar.set(Calendar.MINUTE,0);
            }else if (hour < 18){
                calendar.set(Calendar.HOUR_OF_DAY,hour + 1);
                calendar.set(Calendar.MINUTE,0);
            }else {
                calendar.set(Calendar.HOUR_OF_DAY,9);
                calendar.set(Calendar.MINUTE,0);
            }
            long timeInMillis = calendar.getTimeInMillis();
            long delay = timeInMillis - System.currentTimeMillis();
            Thread.sleep(delay);
        }
    }
}


