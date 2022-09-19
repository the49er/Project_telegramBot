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
public class Bank implements GeneralBotCommand {
    String checkout;
    Long chatId;
    String userName;

    public SendMessage getMessage() {
        log.info("open Bank menu");
        String helloText = "Please choose the Bank";

        SendMessage message = new SendMessage();
        message.setText(helloText);
        message.setChatId(Long.toString(this.chatId));

        InlineKeyboardButton monoBank = InlineKeyboardButton
                .builder()
                .text(this.checkout.equals("monobank") ? "✅ MonoBank" : "MonoBank")
                .callbackData("setBankMonoBank")
                .build();

        InlineKeyboardButton nBU = InlineKeyboardButton
                .builder()
                .text(this.checkout.equals("nbu") ? "✅ NBU" : "NBU")
                .callbackData("setBankNBU")
                .build();

        InlineKeyboardButton privat = InlineKeyboardButton
                .builder()
                .text(this.checkout.equals("privat") ? "✅ Privat" : "Privat")
                .callbackData("setBankPrivat")
                .build();

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList();//raw use + same code with 4 classes
        keyboardButtonsRow1.add(monoBank);
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList();
        keyboardButtonsRow2.add(nBU);
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList();
        keyboardButtonsRow3.add(privat);

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
