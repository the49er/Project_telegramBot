package ua.goit.telegrambot.settings;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Tets {
    public static void main(String[] args) {
        UserService service = UserService.getInstance();
        Calendar calendar = new GregorianCalendar();
        int time = calendar.get(Calendar.HOUR_OF_DAY);
        service.createUser(12345);
        service.createUser(1234);
        service.setSchedulerTime(1234,19);
        service.setSchedulerTime(12345,18);
        List<Integer> userIds = service.getUsersWithNotficationOnCurrentHour(time);
        System.out.println(userIds);
    }
}
