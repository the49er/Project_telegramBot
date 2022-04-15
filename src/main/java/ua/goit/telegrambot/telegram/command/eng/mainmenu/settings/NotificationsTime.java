package ua.goit.telegrambot.telegram.command.eng.mainmenu.settings;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class NotificationsTime extends BotCommand {

    public NotificationsTime() {
        super("notificationstime", "Notifications Time");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {

        String text = "Set a reminder time";
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setChatId(Long.toString(chat.getId()));

        KeyboardButton nineAm = KeyboardButton.builder().text("9:00").build();
        KeyboardButton tenAm = KeyboardButton.builder().text("10:00").build();
        KeyboardButton elevenAm = KeyboardButton.builder().text("11:00").build();

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(nineAm);
        firstRow.add(tenAm);
        firstRow.add(elevenAm);

        KeyboardButton twelweAm = KeyboardButton.builder().text("12:00").build();
        KeyboardButton onePm = KeyboardButton.builder().text("13:00").build();
        KeyboardButton twoPm = KeyboardButton.builder().text("14:00").build();

        KeyboardRow secondRow = new KeyboardRow();
        firstRow.add(twelweAm);
        firstRow.add(onePm);
        firstRow.add(twoPm);

        KeyboardButton threePm = KeyboardButton.builder().text("15:00").build();
        KeyboardButton fourPm = KeyboardButton.builder().text("16:00").build();
        KeyboardButton fivePm = KeyboardButton.builder().text("17:00").build();

        KeyboardRow thirdRow = new KeyboardRow();
        firstRow.add(threePm);
        firstRow.add(fourPm);
        firstRow.add(fivePm);

        KeyboardButton sixPm = KeyboardButton.builder().text("18:00").build();
        KeyboardButton disable_notifications = KeyboardButton.builder().text("Disable notifications").build();

        KeyboardRow fourthRow = new KeyboardRow();
        firstRow.add(sixPm);
        firstRow.add(disable_notifications);

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        // Create the keyboard (list of keyboard rows)

        List<KeyboardRow> timeKeyboard = new ArrayList<>();
        timeKeyboard.add(firstRow);
        timeKeyboard.add(secondRow);
//        timeKeyboard.add(thirdRow);
//        timeKeyboard.add(fourthRow);

        keyboardMarkup.setKeyboard(timeKeyboard);

        message.setReplyMarkup(keyboardMarkup);


        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
