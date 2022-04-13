package ua.goit.telegrambot.telegram;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramCurrencyBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return BotConstants.BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BotConstants.BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            String messageReceived = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            if (!(messageReceived.equals("/start"))) {
                BotConstants.SEND_MESSAGE.setText("You wrote: " + messageReceived);
                BotConstants.SEND_MESSAGE.setChatId(Long.toString(chat_id));
                try {
                    execute(BotConstants.SEND_MESSAGE);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else {
                BotConstants.SEND_MESSAGE.setText("Hello, " + update.getMessage().getFrom().getFirstName() + " this is the CurrencyTelegramBot!");
                BotConstants.SEND_MESSAGE.setChatId(Long.toString(chat_id));
                try {
                    execute(BotConstants.SEND_MESSAGE);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
