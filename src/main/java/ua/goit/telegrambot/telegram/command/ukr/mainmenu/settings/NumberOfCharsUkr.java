package ua.goit.telegrambot.telegram.command.ukr.mainmenu.settings;

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

public class NumberOfCharsUkr extends BotCommand {

    public NumberOfCharsUkr() {
        super("numbofcharsukr", "Вибір кількості десяткових знаків");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {

        String chooseSettingsMessage = "Оберіть, будь ласка, кількість десяткових знаків.";

        SendMessage message = new SendMessage();
        message.setText(chooseSettingsMessage);
        message.setChatId(Long.toString(chat.getId()));

        InlineKeyboardButton twoChars = InlineKeyboardButton
                .builder()
                .text("2")
                .callbackData("2")
                .build();

        InlineKeyboardButton threeChars = InlineKeyboardButton
                .builder()
                .text("3")
                .callbackData("3")
                .build();

        InlineKeyboardButton fourChars = InlineKeyboardButton
                .builder()
                .text("4")
                .callbackData("4")
                .build();


        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(twoChars);

        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(threeChars);

        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        keyboardButtonsRow3.add(fourChars);


        List<List<InlineKeyboardButton>> chooseNumbOfCharsKeyboard = new ArrayList<>();
        chooseNumbOfCharsKeyboard.add(keyboardButtonsRow1);
        chooseNumbOfCharsKeyboard.add(keyboardButtonsRow2);
        chooseNumbOfCharsKeyboard.add(keyboardButtonsRow3);


        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        markup.setKeyboard(chooseNumbOfCharsKeyboard);

        message.setReplyMarkup(markup);

        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}




