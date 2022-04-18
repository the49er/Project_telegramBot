package ua.goit.telegrambot.telegram;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.goit.telegrambot.telegram.nonCommand.NonCommand;
import ua.goit.telegrambot.telegram.command.StartBotCommand;

@Slf4j
public class TelegramCurrencyBot extends TelegramLongPollingCommandBot {

    public TelegramCurrencyBot() {
        register(new StartBotCommand());

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
        Message msg = update.getCallbackQuery().getMessage();
        Long chatId = msg.getChatId();
        String userName = getUserName(msg);
        SendMessage answer = null;
        if (update.hasCallbackQuery()) {
            String callbackQuery = update.getCallbackQuery().getData();
            answer = new NonCommand(callbackQuery,chatId, userName).getAnswer();
            }
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private String getUserName(Message msg) {
        User user = msg.getFrom();
        String userName = user.getUserName();
        return (userName != null) ? userName : String.format("%s %s", user.getLastName(), user.getFirstName());
    }

}
