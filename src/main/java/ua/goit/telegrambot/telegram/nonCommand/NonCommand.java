package ua.goit.telegrambot.telegram.nonCommand;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ua.goit.telegrambot.api.service.MonoCurrencyService;
import ua.goit.telegrambot.api.service.NBUCurrencyService;
import ua.goit.telegrambot.api.service.PrivateBankCurrencyService;
import ua.goit.telegrambot.settings.User;
import ua.goit.telegrambot.settings.UserService;
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

    UserService service = UserService.getInstance();

    NBUCurrencyService nbuCurrencyService = new NBUCurrencyService();
    PrivateBankCurrencyService privateBankCurrencyService = new PrivateBankCurrencyService();
    MonoCurrencyService monoCurrencyService = new MonoCurrencyService();

    public NonCommand(String data, Long chatId, String userName) { // divide into several methods, method should not be longer that 30 lines
        switch(data) {
            case "english":
                answer = new StartEngCommand(chatId, userName).getMessage();
                service.setEnglish(chatId, true);
                service.setUkrainian(chatId, false);
                break;
            case "getInfo":
                answer = new GetInfoCommand(chatId, userName).getMessage();
                break;
            case "settings":
                answer = new SettingsCommand(chatId, userName).getMessage();
                break;
            case "rounding":
                answer = new Rounding(service.getRounding(chatId), chatId, userName).getMessage();
                break;
            case "bank":
                answer = new Bank(service.getBank(chatId), chatId, userName).getMessage();
                break;
            case "currency":
                answer = new Currency(service.getCurrency(chatId), chatId, userName).getMessage();
                break;
            case "notifications":
                answer = new Notifications(String.valueOf(chatId), chatId, userName).getMessage();
                break;
            case "ukrainian":
                service.setEnglish(chatId, false);
                service.setUkrainian(chatId, true);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "getInfoUkr":
                answer = new GetInfoUkrCommand(chatId, userName).getMessage();
                break;
            case "settingsUkr":
                answer = new SettingsUkrCommand(chatId, userName).getMessage();
                break;
            case "roundingUkr":
                answer = new RoundingUkr(service.getRounding(chatId), chatId, userName).getMessage();
                break;
            case "bankUkr":
                answer = new BankUkr(service.getBank(chatId), chatId, userName).getMessage();
                break;
            case "currencyUkr":
                answer = new CurrencyUkr(service.getCurrency(chatId), chatId, userName).getMessage();
                break;
            case "notificationsUkr":
                answer = new NotificationsUkr(String.valueOf(service.getSchedulerTime(chatId)), chatId, userName).getMessage();;
                break;
            //Bank setup for user Eng
            case "setBankMonoBank":
                service.setBank(chatId,"monobank");
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "setBankNBU":
                service.setBank(chatId,"nbu");
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "setBankPrivat":
                service.setBank(chatId,"privat");
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            //Bank setup for user Ukr
            case "setBankMonoBankUkr":
                service.setBank(chatId,"monobank");
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "setBankNbuUkr":
                service.setBank(chatId,"nbu");
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "setBankPrivatUkr":
                service.setBank(chatId,"privat");
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            //Rounding setup for user Eng
            case "setRoundingTwo":
                service.setRounding(chatId,2);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "setRoundingThree":
                service.setRounding(chatId,3);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "setRoundingFour":
                service.setRounding(chatId,4);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            //Rounding setup for user Ukr
            case "setRoundingTwoUkr":
                service.setRounding(chatId,2);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "setRoundingThreeUkr":
                service.setRounding(chatId,3);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "setRoundingFourUkr":
                service.setRounding(chatId,4);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            //Currency setup for user Eng
            case "setCurrencyUSD":
                service.setUsd(chatId,true);
                service.setEur(chatId,false);
                service.setGbp(chatId,false);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "setCurrencyEUR":
                service.setUsd(chatId,false);
                service.setEur(chatId,true);
                service.setGbp(chatId,false);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "setCurrencyGbp":
                service.setUsd(chatId,false);
                service.setEur(chatId,false);
                service.setGbp(chatId,true);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            //Currency setup for user Ukr
            case "setCurrencyUsdUkr":
                service.setUsd(chatId,true);
                service.setEur(chatId,false);
                service.setGbp(chatId,false);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "setCurrencyEurUkr":
                service.setUsd(chatId,false);
                service.setEur(chatId,true);
                service.setGbp(chatId,false);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "setCurrencyGbpUkr":
                service.setUsd(chatId,false);
                service.setEur(chatId,false);
                service.setGbp(chatId,true);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            //Notifications setup for user ENG
            case "9":
                service.setScheduler(chatId,true);
                service.setSchedulerTime(chatId,9);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "10":
                service.setScheduler(chatId,true);
                service.setSchedulerTime(chatId,10);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "11":
                service.setScheduler(chatId,true);
                service.setSchedulerTime(chatId,11);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "12":
                service.setScheduler(chatId,true);
                service.setSchedulerTime(chatId,12);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "13":
                service.setScheduler(chatId,true);
                service.setSchedulerTime(chatId,13);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "14":
                service.setScheduler(chatId,true);
                service.setSchedulerTime(chatId,14);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "15":
                service.setScheduler(chatId,true);
                service.setSchedulerTime(chatId,15);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "16":
                service.setScheduler(chatId,true);
                service.setSchedulerTime(chatId,16);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "17":
                service.setScheduler(chatId,true);
                service.setSchedulerTime(chatId,17);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "18":
                service.setScheduler(chatId,true);
                service.setSchedulerTime(chatId,18);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "cancelNotifications":
                service.setScheduler(chatId,false);
                service.setSchedulerTime(chatId,-1);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            //Notifications setup for user ENG
            case "setNine":
                service.setScheduler(chatId,true);
                service.setSchedulerTime(chatId,9);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "setTen":
                service.setScheduler(chatId,true);
                service.setSchedulerTime(chatId,10);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "setEleven":
                service.setScheduler(chatId,true);
                service.setSchedulerTime(chatId,11);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "setTwelve":
                service.setScheduler(chatId,true);
                service.setSchedulerTime(chatId,12);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "setThirteen":
                service.setScheduler(chatId,true);
                service.setSchedulerTime(chatId,13);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "setFourteen":
                service.setScheduler(chatId,true);
                service.setSchedulerTime(chatId,14);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "setFifteen":
                service.setScheduler(chatId,true);
                service.setSchedulerTime(chatId,15);
                answer = new StartUkrCommand(chatId, userName).getMessage();

                break;
            case "setSixteen":
                service.setScheduler(chatId,true);
                service.setSchedulerTime(chatId,16);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "setSeventeen":
                service.setScheduler(chatId,true);
                service.setSchedulerTime(chatId,17);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "setEighteen":
                service.setScheduler(chatId,true);
                service.setSchedulerTime(chatId,18);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "cancelNotificationsUkr":
                service.setScheduler(chatId,false);
                service.setSchedulerTime(chatId,-1);
                answer = new StartUkrCommand(chatId, userName).getMessage();

        }
    }

    public SendMessage getAnswer() {
        return answer;
    }

}
