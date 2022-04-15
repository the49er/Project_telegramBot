package ua.goit.telegrambot.telegram.command.eng.mainmenu.settings;

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

public class NotificationsTime extends BotCommand {

    public NotificationsTime() {
        super("notificationstime", "Notifications Time");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {

        String text = "Set a reminder time \uD83D\uDD14";
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setChatId(Long.toString(chat.getId()));

        InlineKeyboardButton nineAm = InlineKeyboardButton
                .builder()
                .text("9:00")
                .callbackData("9:00")
                .build();

        InlineKeyboardButton tenAm = InlineKeyboardButton
                .builder()
                .text("10:00")
                .callbackData("10:00")
                .build();

        InlineKeyboardButton elevenAm = InlineKeyboardButton
                .builder()
                .text("11:00")
                .callbackData("11:00")
                .build();

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(nineAm);
        keyboardButtonsRow1.add(tenAm);
        keyboardButtonsRow1.add(elevenAm);

        InlineKeyboardButton twelweAm = InlineKeyboardButton
                .builder()
                .text("12:00")
                .callbackData("12:00")
                .build();

        InlineKeyboardButton onePm = InlineKeyboardButton
                .builder()
                .text("13:00")
                .callbackData("13:00")
                .build();

        InlineKeyboardButton twoPm = InlineKeyboardButton
                .builder()
                .text("14:00")
                .callbackData("14:00")
                .build();

        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(twelweAm);
        keyboardButtonsRow2.add(onePm);
        keyboardButtonsRow2.add(twoPm);

        InlineKeyboardButton threePm = InlineKeyboardButton
                .builder()
                .text("15:00")
                .callbackData("15:00")
                .build();

        InlineKeyboardButton fourPm = InlineKeyboardButton
                .builder()
                .text("16:00")
                .callbackData("16:00")
                .build();

        InlineKeyboardButton fivePm = InlineKeyboardButton
                .builder()
                .text("17:00")
                .callbackData("17:00")
                .build();

        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        keyboardButtonsRow3.add(threePm);
        keyboardButtonsRow3.add(fourPm);
        keyboardButtonsRow3.add(fivePm);

        InlineKeyboardButton sixPm = InlineKeyboardButton
                .builder()
                .text("18:00")
                .callbackData("18:00")
                .build();

        InlineKeyboardButton disable_notifications = InlineKeyboardButton
                .builder()
                .text("Disable notifications \uD83D\uDD15")
                .callbackData("Disable notifications")
                .build();

        List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();
        keyboardButtonsRow4.add(sixPm);
        keyboardButtonsRow4.add(disable_notifications);


        List<List<InlineKeyboardButton>> timeKeyboard = new ArrayList<>();
        timeKeyboard.add(keyboardButtonsRow1);
        timeKeyboard.add(keyboardButtonsRow2);
        timeKeyboard.add(keyboardButtonsRow3);
        timeKeyboard.add(keyboardButtonsRow4);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        keyboardMarkup.setKeyboard(timeKeyboard);

        message.setReplyMarkup(keyboardMarkup);

        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
