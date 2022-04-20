package ua.goit.telegrambot.telegram.nonCommand.ukr;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ua.goit.telegrambot.telegram.nonCommand.GeneralBotCommand;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
public class SettingsUkrCommand implements GeneralBotCommand {
    Long chatId;
    String userName;

    public SendMessage getMessage() {
        log.info("open settingsUkr menu");
        String helloText = "Налаштування";

        SendMessage message = new SendMessage();
        message.setText(helloText);
        message.setChatId(Long.toString(this.chatId));
        InlineKeyboardButton numberOfSymb = InlineKeyboardButton.builder()
                .text("Заокруглення ↩️")
                .callbackData("roundingUkr")
                .build();

        InlineKeyboardButton bank = InlineKeyboardButton
                .builder()
                .text("Банк \uD83C\uDFE6")
                .callbackData("bankUkr")
                .build();

        InlineKeyboardButton currencies = InlineKeyboardButton
                .builder()
                .text("Валюта \uD83D\uDCB2")
                .callbackData("currencyUkr")
                .build();

        InlineKeyboardButton notificationTime = InlineKeyboardButton.builder()
                .text("Нагадування \uD83D\uDCAC")
                .callbackData("notificationsUkr")
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
