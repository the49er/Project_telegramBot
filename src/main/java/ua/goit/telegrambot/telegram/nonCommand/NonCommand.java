package ua.goit.telegrambot.telegram.nonCommand;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ua.goit.telegrambot.settings.User;
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
    User user;

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
                answer = new Rounding(user.getRounding(), chatId, userName).getMessage();
                break;
            case "bank":
                answer = new Bank(user.getBank(), chatId, userName).getMessage();
                break;
            case "currency":
                answer = new Currency("usd"/*TODO user.getCurreny()*/, chatId, userName).getMessage();
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
                answer = new RoundingUkr(user.getRounding(), chatId, userName).getMessage();
                break;
            case "bankUkr":
                answer = new BankUkr(user.getBank(), chatId, userName).getMessage();
                break;
            case "currencyUkr":
                answer = new CurrencyUkr("usd"/*TODO user.getCurreny()*/, chatId, userName).getMessage();
                break;
            case "notificationsUkr":
                answer = new NotificationsUkr(chatId, userName).getMessage();
                break;
            //Bank setup for user
            case "setBankMonoBank":
                user.setBank("monobank");
                break;
            case "setBankNBU":
                user.setBank("nbu");
                break;
            case "setBankPrivat":
                user.setBank("privat");
                break;
            //Rounding setup for user
            case "setRoundingTwo":
                user.setRounding(2);
                break;
            case "setRoundingThree":
                user.setRounding(3);
                break;
            case "setRoundingFour":
                user.setRounding(4);
                break;
            //Currency setup for user
            case "setCurrencyUSD":
                //TODO setCurrency("usd") or BETTER setCurrency(CURRENCY.USD);
                break;
            case "setCurrencyEUR":
                //TODO setCurrency("eur") or BETTER setCurrency(CURRENCY.EUR);
                break;
            case "setCurrencyRUR":
                //TODO setCurrency("rur") or BETTER setCurrency(CURRENCY.RUR);
                break;
            //Notifications setup for user
            case "9":
                user.setScheduler(true);
                user.setSchedulerTime(9);
                break;
            case "10":
                user.setScheduler(true);
                user.setSchedulerTime(10);
                break;
            case "11":
                user.setScheduler(true);
                user.setSchedulerTime(11);
                break;
            case "12":
                user.setScheduler(true);
                user.setSchedulerTime(12);
                break;
            case "13":
                user.setScheduler(true);
                user.setSchedulerTime(13);
                break;
            case "14":
                user.setScheduler(true);
                user.setSchedulerTime(14);
                break;
            case "15":
                user.setScheduler(true);
                user.setSchedulerTime(15);
                break;
            case "16":
                user.setScheduler(true);
                user.setSchedulerTime(16);
                break;
            case "17":
                user.setScheduler(true);
                user.setSchedulerTime(17);
                break;
            case "cancelNotifications":
                user.setScheduler(false);
                break;

        }
    }

    public SendMessage getAnswer() {
        return answer;
    }

}
