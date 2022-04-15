package ua.goit.telegrambot.telegram.command.eng;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class StartEngCommand extends BotCommand{

    public StartEngCommand() {
        super("starteng", "Start the Bot with English");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        String helloText = "This bot will help you get the current exchange rate.";

        SendMessage message = new SendMessage();
        message.setText(helloText);
        message.setChatId(Long.toString(chat.getId()));

        InlineKeyboardButton getInfo = InlineKeyboardButton
                .builder()
                .text("Get info ℹ️")
                .callbackData("Get info")
                .build();

        InlineKeyboardButton settings = InlineKeyboardButton
                .builder()
                .text("Settings \uD83D\uDD27")
                .callbackData("Settings")
                .build();

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(getInfo);

        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(settings);

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(keyboardButtonsRow1);
        keyboard.add(keyboardButtonsRow2);

        markup.setKeyboard(keyboard);

        message.setReplyMarkup(markup);

        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
