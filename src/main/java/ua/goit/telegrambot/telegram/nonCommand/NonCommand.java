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
                answer = new Rounding(service.getRounding(Math.toIntExact(chatId)),chatId, userName).getMessage();
                break;
            case "bank":
                answer = new Bank(service.getBank(Math.toIntExact(chatId)), chatId, userName).getMessage();
                break;
            case "currency":
                answer = new Currency(service.getCurrency(Math.toIntExact(chatId)), chatId, userName).getMessage();
                break;
            case "notifications":
                answer = new Notifications(String.valueOf(service.getSchedulerTime(Math.toIntExact(chatId))), chatId, userName).getMessage();
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
                answer = new RoundingUkr(service.getRounding(Math.toIntExact(chatId)), chatId, userName).getMessage();
                break;
            case "bankUkr":
                answer = new BankUkr(service.getBank(Math.toIntExact(chatId)), chatId, userName).getMessage();
                break;
            case "currencyUkr":
                answer = new CurrencyUkr(service.getCurrency(Math.toIntExact(chatId)), chatId, userName).getMessage();
                break;
            case "notificationsUkr":
                answer = new NotificationsUkr(String.valueOf(service.getSchedulerTime(Math.toIntExact(chatId))), chatId, userName).getMessage();;
                break;
            //Bank setup for user Eng
            case "setBankMonoBank":
                service.setBank(Math.toIntExact(chatId),"monobank");
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "setBankNBU":
                service.setBank(Math.toIntExact(chatId),"nbu");
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "setBankPrivat":
                service.setBank(Math.toIntExact(chatId),"privat");
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            //Bank setup for user Ukr
            case "setBankMonoBankUkr":
                service.setBank(Math.toIntExact(chatId),"monobank");
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "etBankNbuUkr":
                service.setBank(Math.toIntExact(chatId),"nbu");
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "setBankPrivatUkr":
                service.setBank(Math.toIntExact(chatId),"privat");
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            //Rounding setup for user Eng
            case "setRoundingTwo":
                service.setRounding(Math.toIntExact(chatId),2);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "setRoundingThree":
                service.setRounding(Math.toIntExact(chatId),3);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "setRoundingFour":
                service.setRounding(Math.toIntExact(chatId),4);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            //Rounding setup for user Eng
            case "setRoundingTwoUkr":
                service.setRounding(Math.toIntExact(chatId),2);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "setRoundingThreeUkr":
                service.setRounding(Math.toIntExact(chatId),3);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "setRoundingFourUkr":
                service.setRounding(Math.toIntExact(chatId),4);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            //Currency setup for user Eng
            case "setCurrencyUSD":
                service.setUsd(Math.toIntExact(chatId),true);
                service.setEur(Math.toIntExact(chatId),false);
                service.setGbp(Math.toIntExact(chatId),false);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "setCurrencyEUR":
                service.setUsd(Math.toIntExact(chatId),false);
                service.setEur(Math.toIntExact(chatId),true);
                service.setGbp(Math.toIntExact(chatId),false);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "setCurrencyGbp":
                service.setUsd(Math.toIntExact(chatId),false);
                service.setEur(Math.toIntExact(chatId),false);
                service.setGbp(Math.toIntExact(chatId),true);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            //Currency setup for user Ukr
            case "setCurrencyUsdUkr":
                service.setUsd(Math.toIntExact(chatId),true);
                service.setEur(Math.toIntExact(chatId),false);
                service.setGbp(Math.toIntExact(chatId),false);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "setCurrencyEurUkr":
                service.setUsd(Math.toIntExact(chatId),false);
                service.setEur(Math.toIntExact(chatId),true);
                service.setGbp(Math.toIntExact(chatId),false);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "setCurrencyGbpUkr":
                service.setUsd(Math.toIntExact(chatId),false);
                service.setEur(Math.toIntExact(chatId),false);
                service.setGbp(Math.toIntExact(chatId),true);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            //Notifications setup for user ENG
            case "9":
                service.setScheduler(Math.toIntExact(chatId),true);
                service.setSchedulerTime(Math.toIntExact(chatId),9);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "10":
                service.setScheduler(Math.toIntExact(chatId),true);
                service.setSchedulerTime(Math.toIntExact(chatId),10);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "11":
                service.setScheduler(Math.toIntExact(chatId),true);
                service.setSchedulerTime(Math.toIntExact(chatId),11);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "12":
                service.setScheduler(Math.toIntExact(chatId),true);
                service.setSchedulerTime(Math.toIntExact(chatId),12);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "13":
                service.setScheduler(Math.toIntExact(chatId),true);
                service.setSchedulerTime(Math.toIntExact(chatId),13);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "14":
                service.setScheduler(Math.toIntExact(chatId),true);
                service.setSchedulerTime(Math.toIntExact(chatId),14);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "15":
                service.setScheduler(Math.toIntExact(chatId),true);
                service.setSchedulerTime(Math.toIntExact(chatId),15);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "16":
                service.setScheduler(Math.toIntExact(chatId),true);
                service.setSchedulerTime(Math.toIntExact(chatId),16);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "17":
                service.setScheduler(Math.toIntExact(chatId),true);
                service.setSchedulerTime(Math.toIntExact(chatId),17);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "18":
                service.setScheduler(Math.toIntExact(chatId),true);
                service.setSchedulerTime(Math.toIntExact(chatId),18);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            case "cancelNotifications":
                service.setScheduler(Math.toIntExact(chatId),false);
                answer = new StartEngCommand(chatId, userName).getMessage();
                break;
            //Notifications setup for user ENG
            case "setNine":
                service.setScheduler(Math.toIntExact(chatId),true);
                service.setSchedulerTime(Math.toIntExact(chatId),9);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "setTen":
                service.setScheduler(Math.toIntExact(chatId),true);
                service.setSchedulerTime(Math.toIntExact(chatId),10);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "setEleven":
                service.setScheduler(Math.toIntExact(chatId),true);
                service.setSchedulerTime(Math.toIntExact(chatId),11);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "setTwelve":
                service.setScheduler(Math.toIntExact(chatId),true);
                service.setSchedulerTime(Math.toIntExact(chatId),12);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "setThirteen":
                service.setScheduler(Math.toIntExact(chatId),true);
                service.setSchedulerTime(Math.toIntExact(chatId),13);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "setFourteen":
                service.setScheduler(Math.toIntExact(chatId),true);
                service.setSchedulerTime(Math.toIntExact(chatId),14);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "setFifteen":
                service.setScheduler(Math.toIntExact(chatId),true);
                service.setSchedulerTime(Math.toIntExact(chatId),15);
                answer = new StartUkrCommand(chatId, userName).getMessage();

                break;
            case "setSixteen":
                service.setScheduler(Math.toIntExact(chatId),true);
                service.setSchedulerTime(Math.toIntExact(chatId),16);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "setSeventeen":
                service.setScheduler(Math.toIntExact(chatId),true);
                service.setSchedulerTime(Math.toIntExact(chatId),17);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "setEighteen":
                service.setScheduler(Math.toIntExact(chatId),true);
                service.setSchedulerTime(Math.toIntExact(chatId),18);
                answer = new StartUkrCommand(chatId, userName).getMessage();
                break;
            case "cancelNotificationsUkr":
                service.setScheduler(Math.toIntExact(chatId),false);
                answer = new StartUkrCommand(chatId, userName).getMessage();

        }
    }

    public SendMessage getAnswer() {
        return answer;
    }

}
