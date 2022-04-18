package ua.goit.telegrambot.telegram.command;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Collections;
import java.util.List;

public class StartBotCommand extends BotCommand {

    public StartBotCommand() {
        super("start", "Start the Bot");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        String helloText = "Будь ласка, оберіть мову.\nPlease select your language.";

        SendMessage message = new SendMessage();
        message.setText(helloText);
        message.setChatId(Long.toString(chat.getId()));

        InlineKeyboardButton chooseENG = InlineKeyboardButton
                .builder()
                .text("English\uD83C\uDDFA\uD83C\uDDF8")
                .callbackData("english")
                .build();

        InlineKeyboardButton chooseUKR = InlineKeyboardButton
                .builder()
                .text("Українська\uD83C\uDDFA\uD83C\uDDE6")
                .callbackData("ukrainian")
                .build();

        InlineKeyboardMarkup keyboard = InlineKeyboardMarkup
                .builder()
                .keyboard(Collections.singleton(List.of(chooseUKR, chooseENG)))
                .build();

        message.setReplyMarkup(keyboard);

        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}