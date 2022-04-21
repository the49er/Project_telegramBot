package ua.goit.telegrambot.telegram.nonCommand.ukr.settings;

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
public class RoundingUkr implements GeneralBotCommand {
    int checkout;
    Long chatId;
    String userName;

    public SendMessage getMessage() {
        log.info("roundingUkr option");
        String helloText = "Будь ласка, оберіть необхідну кількість знаків після коми";

        SendMessage message = new SendMessage();
        message.setText(helloText);
        message.setChatId(Long.toString(this.chatId));

        InlineKeyboardButton two = InlineKeyboardButton
                .builder()
                .text(checkout == 2 ? "✅ 2" : "2")
                .callbackData("setRoundingTwoUkr")
                .build();

        InlineKeyboardButton three = InlineKeyboardButton
                .builder()
                .text(checkout == 3 ? "✅ 3" : "3")
                .callbackData("setRoundingThreeUkr")
                .build();

        InlineKeyboardButton four = InlineKeyboardButton
                .builder()
                .text(checkout == 4 ? "✅ 4" : "4")
                .callbackData("setRoundingFourUkr").build();

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
