package ua.goit.telegrambot.telegram.command.ukr;

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

public class StartUkrCommand extends BotCommand{

    public StartUkrCommand() {
        super("startukr", "Запустити бот українською");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        String helloText = "Цей бот допоможе вам отримати поточний курс обміну.";

        SendMessage message = new SendMessage();
        message.setText(helloText);
        message.setChatId(Long.toString(chat.getId()));

        InlineKeyboardButton getInfo = InlineKeyboardButton
                .builder()
                .text("Отримати інформацію ℹ️")
                .callbackData("Отримати інформацію")
                .build();

        InlineKeyboardButton settings = InlineKeyboardButton
                .builder()
                .text("Налаштування \uD83D\uDD27")
                .callbackData("Налаштування")
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
