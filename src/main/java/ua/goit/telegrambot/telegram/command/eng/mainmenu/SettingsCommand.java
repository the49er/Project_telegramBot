package ua.goit.telegrambot.telegram.command.eng.mainmenu;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SettingsCommand extends BotCommand {

    public SettingsCommand(){
        super("settings","Settings");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {

        String chooseSettingsMessage = "Please select a setting";

        SendMessage message = new SendMessage();
        message.setText(chooseSettingsMessage);
        message.setChatId(Long.toString(chat.getId()));

        InlineKeyboardButton bank = InlineKeyboardButton
                .builder()
                .text("Bank")
                .callbackData("Choose a bank")
                .build();

        InlineKeyboardButton currencies = InlineKeyboardButton
                .builder()
                .text("Currencies")
                .callbackData("Currencies")
                .build();

        InlineKeyboardButton notificationTime = InlineKeyboardButton
                .builder()
                .text("Notification")
                .callbackData("Notification time")
                .build();

        InlineKeyboardButton numberOfSymb = InlineKeyboardButton
                .builder()
                .text("Rounding")
                .callbackData("Number of symbols after comma")
                .build();


        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(bank);

        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(currencies);

        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        keyboardButtonsRow3.add(notificationTime);

        List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();
        keyboardButtonsRow4.add(numberOfSymb);

        List<List<InlineKeyboardButton>> settingsKeyboard = new ArrayList<>();
        settingsKeyboard.add(keyboardButtonsRow1);
        settingsKeyboard.add(keyboardButtonsRow2);
        settingsKeyboard.add(keyboardButtonsRow3);
        settingsKeyboard.add(keyboardButtonsRow4);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        markup.setKeyboard(settingsKeyboard);

        message.setReplyMarkup(markup);


        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
