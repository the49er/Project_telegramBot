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
public class Currency implements GeneralBotCommand {
    String checkout;
    Long chatId;
    String userName;

    public SendMessage getMessage() {
        log.info("open currency menu");
        String helloText = "Please choose the currency \uD83D\uDCB2";

        SendMessage message = new SendMessage();
        message.setText(helloText);
        message.setChatId(Long.toString(this.chatId));

        InlineKeyboardButton usd = InlineKeyboardButton
                .builder()
                .text(this.checkout.equals("usd") ? "✅ USD" : "USD")
                .callbackData("setCurrencyUSD")
                .build();

        InlineKeyboardButton eur = InlineKeyboardButton
                .builder()
                .text(this.checkout.equals("eur") ? "✅ EUR" : "EUR")
                .callbackData("setCurrencyEUR")
                .build();

        InlineKeyboardButton gbp = InlineKeyboardButton
                .builder()
                .text(this.checkout.equals("gbp") ? "✅ GBP" : "GBP")
                .callbackData("setCurrencyGbp")
                .build();

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList();
        keyboardButtonsRow1.add(usd);
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList();
        keyboardButtonsRow2.add(eur);
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList();
        keyboardButtonsRow3.add(gbp);

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
