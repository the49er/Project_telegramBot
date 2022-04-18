package ua.goit.telegrambot.telegram.nonCommand;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ua.goit.telegrambot.telegram.nonCommand.eng.GetInfoCommand;
import ua.goit.telegrambot.telegram.nonCommand.eng.SettingsCommand;
import ua.goit.telegrambot.telegram.nonCommand.eng.StartEngCommand;
import ua.goit.telegrambot.telegram.nonCommand.eng.mainmenu.settings.Bank;
import ua.goit.telegrambot.telegram.nonCommand.eng.mainmenu.settings.Currency;
import ua.goit.telegrambot.telegram.nonCommand.eng.mainmenu.settings.Notifications;
import ua.goit.telegrambot.telegram.nonCommand.eng.mainmenu.settings.Rounding;
import ua.goit.telegrambot.telegram.nonCommand.ukr.GetInfoUkrCommand;
import ua.goit.telegrambot.telegram.nonCommand.ukr.SettingsUkrCommand;
import ua.goit.telegrambot.telegram.nonCommand.ukr.StartUkrCommand;
import ua.goit.telegrambot.telegram.nonCommand.ukr.settings.BankUkr;
import ua.goit.telegrambot.telegram.nonCommand.ukr.settings.CurrencyUkr;
import ua.goit.telegrambot.telegram.nonCommand.ukr.settings.NotificationsUkr;
import ua.goit.telegrambot.telegram.nonCommand.ukr.settings.RoundingUkr;

@Slf4j
@AllArgsConstructor
public class NonCommand {
    SendMessage answer;

    public NonCommand(String data, Long chatId, String userName) {
        switch(data) {
            case "english":
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "getInfo":
                answer = new GetInfoCommand(chatId, userName).getMessage();
                break;
            case "settings":
                answer = new SettingsCommand(chatId, userName).getMessage();
                break;
            case "rounding":
                answer = new Rounding("two", chatId, userName).getMessage();
                break;
            case "bank":
                answer = new Bank("monobank", chatId, userName).getMessage();
                break;
            case "currency":
                answer = new Currency("usd", chatId, userName).getMessage();
                break;
            case "notifications":
                answer = new Notifications(chatId, userName).getMessage();
                break;
            case "ukrainian":
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "getInfoUkr":
                answer = new GetInfoUkrCommand(chatId, userName).getMessage();
                break;
            case "settingsUkr":
                answer = new SettingsUkrCommand(chatId, userName).getMessage();
                break;
            case "roundingUkr":
                answer = new RoundingUkr("two", chatId, userName).getMessage();
                break;
            case "bankUkr":
                answer = new BankUkr("monobank", chatId, userName).getMessage();
                break;
            case "currencyUkr":
                answer = new CurrencyUkr("usd", chatId, userName).getMessage();
                break;
            case "notificationsUkr":
                answer = new NotificationsUkr(chatId, userName).getMessage();
                break;

        }
    }

    public SendMessage getAnswer() {
        return answer;
    }

}
