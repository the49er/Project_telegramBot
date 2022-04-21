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
public class BankUkr implements GeneralBotCommand {
    String checkout;
    Long chatId;
    String userName;

    public SendMessage getMessage() {
        log.info("open BankUkr menu");
        String helloText = "Будь ласка, оберіть банківську установу";

        SendMessage message = new SendMessage();
        message.setText(helloText);
        message.setChatId(Long.toString(this.chatId));

        InlineKeyboardButton monoBank = InlineKeyboardButton
                .builder()
                .text(this.checkout.equals("monobank") ? "✅ МоноБанк" : "МоноБанк")
                .callbackData("setBankMonoBankUkr")
                .build();

        InlineKeyboardButton nbu = InlineKeyboardButton
                .builder()
                .text(this.checkout.equals("nbu") ? "✅ НБУ" : "НБУ")
                .callbackData("setBankNbuUkr")
                .build();

        InlineKeyboardButton privat = InlineKeyboardButton
                .builder()
                .text(this.checkout.equals("privat") ? "✅ ПриватБанк" : "ПриватБанк")
                .callbackData("setBankPrivatUkr")
                .build();

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList();
        keyboardButtonsRow1.add(monoBank);
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList();
        keyboardButtonsRow2.add(nbu);
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
