package ua.goit.telegrambot.telegram;


import ua.goit.telegrambot.api.dto.threads.MonoBankApiRate;

public class TelegramCurrencyBotTest {

    public static void main(String[] args) {
        MonoBankApiRate monoBankApiRate = new MonoBankApiRate();
        monoBankApiRate.start();
    }
}
