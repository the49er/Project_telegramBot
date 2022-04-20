package ua.goit.telegrambot.telegram.nonCommand.eng.mainmenu.settings;

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
public class Rounding implements GeneralBotCommand {

    int checkout;
    Long chatId;
    String userName;

    public SendMessage getMessage() {
        log.info("open rounding menu");
        String helloText = "Please choose the rounding";

        SendMessage message = new SendMessage();
        message.setText(helloText);
        message.setChatId(Long.toString(this.chatId));

        InlineKeyboardButton two = InlineKeyboardButton
                .builder()
                .text(checkout == 2 ? "✅ 2" : "2")
                .callbackData("setRoundingTwo")
                .build();

        InlineKeyboardButton three = InlineKeyboardButton
                .builder()
                .text(checkout == 3 ? "✅ 3" : "3")
                .callbackData("setRoundingThree")
                .build();

        InlineKeyboardButton four = InlineKeyboardButton
                .builder()
                .text(checkout == 4 ? "✅ 4" : "4")
                .callbackData("setRoundingFour")
                .build();

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList();
        keyboardButtonsRow1.add(two);
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList();
        keyboardButtonsRow2.add(three);
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList();
        keyboardButtonsRow3.add(four);

        List<List<InlineKeyboardButton>> settingsKeyboard = new ArrayList();
        settingsKeyboard.add(keyboardButtonsRow1);
        settingsKeyboard.add(keyboardButtonsRow2);
        settingsKeyboard.add(keyboardButtonsRow3);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(settingsKeyboard);
        message.setReplyMarkup(markup);
        return message;
    }

}
