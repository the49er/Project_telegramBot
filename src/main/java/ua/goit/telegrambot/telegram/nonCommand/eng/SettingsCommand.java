package ua.goit.telegrambot.telegram.nonCommand.eng;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ua.goit.telegrambot.telegram.nonCommand.GeneralBotCommand;

@Slf4j
@AllArgsConstructor
public class SettingsCommand implements GeneralBotCommand {
    Long chatId;
    String userName;

    public SendMessage getMessage() {
        log.info("open settings menu");
        String helloText = "Settings";

        SendMessage message = new SendMessage();
        message.setText(helloText);
        message.setChatId(Long.toString(this.chatId));

        InlineKeyboardButton numberOfSymb = InlineKeyboardButton
                .builder()
                .text("Rounding ↩️")
                .callbackData("rounding")
                .build();

        InlineKeyboardButton bank = InlineKeyboardButton
                .builder()
                .text("Bank \uD83C\uDFE6").callbackData("bank")
                .build();

        InlineKeyboardButton currencies = InlineKeyboardButton
                .builder()
                .text("Currencies \uD83D\uDCB2")
                .callbackData("currency")
                .build();

        InlineKeyboardButton notificationTime = InlineKeyboardButton
                .builder()
                .text("Notification \uD83D\uDCAC").callbackData("notifications")
                .build();

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList();
        keyboardButtonsRow1.add(numberOfSymb);
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList();
        keyboardButtonsRow2.add(bank);
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList();
        keyboardButtonsRow3.add(currencies);
        List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList();
        keyboardButtonsRow4.add(notificationTime);

        List<List<InlineKeyboardButton>> settingsKeyboard = new ArrayList();
        settingsKeyboard.add(keyboardButtonsRow1);
        settingsKeyboard.add(keyboardButtonsRow2);
        settingsKeyboard.add(keyboardButtonsRow3);
        settingsKeyboard.add(keyboardButtonsRow4);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(settingsKeyboard);
        message.setReplyMarkup(markup);
        return message;
    }

}
