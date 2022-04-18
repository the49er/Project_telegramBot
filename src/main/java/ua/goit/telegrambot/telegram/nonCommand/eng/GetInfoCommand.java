package ua.goit.telegrambot.telegram.nonCommand.eng;

import java.text.MessageFormat;
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
public class GetInfoCommand implements GeneralBotCommand {
    Long chatId;
    String userName;

    public SendMessage getMessage() {
        log.info("receive getInfo response");
        String bankName = "MonoBank";
        String currencyPair = "USD/UAH";

        double purchaseRate = 27.55D;
        double saleRate = 27.95D;

        String helloText = MessageFormat
                .format("The exchange rate in {0}: {1}\n Purchase: {2}\n Sale: {3}", bankName, currencyPair, purchaseRate, saleRate);

        SendMessage message = new SendMessage();
        message.setText(helloText);
        message.setChatId(Long.toString(this.chatId));

        InlineKeyboardButton getInfo = InlineKeyboardButton
                .builder()
                .text("Get info ℹ️")
                .callbackData("getInfo")
                .build();

        InlineKeyboardButton settings = InlineKeyboardButton
                .builder()
                .text("Settings \ud83d\udd27")
                .callbackData("settings")
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
