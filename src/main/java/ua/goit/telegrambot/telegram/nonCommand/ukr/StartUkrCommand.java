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
public class StartUkrCommand implements GeneralBotCommand {
    Long chatId;
    String userName;

    public SendMessage getMessage() {
        log.info("open startUkr menu");
        String helloText = "Головне меню: ";

        SendMessage message = new SendMessage();
        message.setText(helloText);
        message.setChatId(Long.toString(this.chatId));

        InlineKeyboardButton getInfo = InlineKeyboardButton
                .builder()
                .text("Отримати інформацію \uD83D\uDCB1")
                .callbackData("getInfoUkr")
                .build();

        InlineKeyboardButton settings = InlineKeyboardButton
                .builder()
                .text("Налаштування \ud83d\udd27")
                .callbackData("settingsUkr")
                .build();

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList();
        keyboardButtonsRow1.add(getInfo);
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList();
        keyboardButtonsRow2.add(settings);

        List<List<InlineKeyboardButton>> keyboard = new ArrayList();
        keyboard.add(keyboardButtonsRow1);
        keyboard.add(keyboardButtonsRow2);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);
        return message;
    }

}
