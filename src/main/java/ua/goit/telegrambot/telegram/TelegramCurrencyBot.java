package ua.goit.telegrambot.telegram;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.goit.telegrambot.telegram.command.eng.mainmenu.GetInfoCommand;
import ua.goit.telegrambot.telegram.command.eng.mainmenu.SettingsCommand;
import ua.goit.telegrambot.telegram.command.StartBotCommand;
import ua.goit.telegrambot.telegram.command.eng.StartEngCommand;
import ua.goit.telegrambot.telegram.command.eng.mainmenu.settings.ChooseABank;
import ua.goit.telegrambot.telegram.command.eng.mainmenu.settings.ChooseСurrencies;
import ua.goit.telegrambot.telegram.command.eng.mainmenu.settings.NotificationsTime;
import ua.goit.telegrambot.telegram.command.eng.mainmenu.settings.NumberOfChars;
import ua.goit.telegrambot.telegram.command.ukr.StartUkrCommand;
import ua.goit.telegrambot.telegram.command.ukr.mainmenu.GetInfoCommandUkr;
import ua.goit.telegrambot.telegram.command.ukr.mainmenu.SettingsCommandUkr;
import ua.goit.telegrambot.telegram.command.ukr.mainmenu.settings.ChooseABankUkr;
import ua.goit.telegrambot.telegram.command.ukr.mainmenu.settings.ChooseСurrenciesUkr;
import ua.goit.telegrambot.telegram.command.ukr.mainmenu.settings.NotificationsTimeUkr;
import ua.goit.telegrambot.telegram.command.ukr.mainmenu.settings.NumberOfCharsUkr;

import java.util.List;

public class TelegramCurrencyBot extends TelegramLongPollingCommandBot {

    public TelegramCurrencyBot() {
        register(new StartBotCommand());

        //language menu
        register(new StartEngCommand());
        register(new StartUkrCommand());

        //eng main menu
        register(new SettingsCommand());
        register(new GetInfoCommand());

        //ukr main menu
        register(new SettingsCommandUkr());
        register(new GetInfoCommandUkr());

        //eng settings menu
        register(new NotificationsTime());
        register(new ChooseABank());
        register(new NumberOfChars());
        register(new ChooseСurrencies());

        //ukr settings menu
        register(new NotificationsTimeUkr());
        register(new ChooseABankUkr());
        register(new NumberOfCharsUkr());
        register(new ChooseСurrenciesUkr());
    }

    @Override
    public String getBotUsername() {
        return BotConstants.BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BotConstants.BOT_TOKEN;
    }

    @Override
    public void processNonCommandUpdate(Update update) {

        if (update.hasCallbackQuery()) {
            String callbackQuery = update.getCallbackQuery().getData();
//                //if(callbackQuery == "English"){
//
//                SendMessage responseMessage = new SendMessage();
//                    responseMessage.getClass();
            //}
            System.out.println("callbackQuery = " + callbackQuery);
        }

        if (update.hasMessage() && update.getMessage().hasText()) {

            String messageReceived = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();


            if (!(messageReceived.equals("/start"))) {
                BotConstants.SEND_MESSAGE.setText("Sorry, I don't know this command!");
                BotConstants.SEND_MESSAGE.setChatId(Long.toString(chat_id));
                try {
                    execute(BotConstants.SEND_MESSAGE);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
//            } else {
//                BotConstants.SEND_MESSAGE.setText("Hi " + update.getMessage().getFrom().getFirstName() + "!\nThis bot will help you get the current exchange rate.");
//                BotConstants.SEND_MESSAGE.setChatId(Long.toString(chat_id));
//                try {
//                    execute(BotConstants.SEND_MESSAGE);
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }
}
