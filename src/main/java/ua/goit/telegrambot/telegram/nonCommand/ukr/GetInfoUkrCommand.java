package ua.goit.telegrambot.telegram.nonCommand.ukr;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ua.goit.telegrambot.api.dto.Currency;
import ua.goit.telegrambot.api.service.MonoCurrencyService;
import ua.goit.telegrambot.settings.UserService;
import ua.goit.telegrambot.telegram.nonCommand.GeneralBotCommand;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
public class GetInfoUkrCommand implements GeneralBotCommand {
    Long chatId;
    String userName;

    public SendMessage getMessage() {
        UserService service = UserService.getInstance();
        log.info("open BankUkr menu");

        String bankName = "NBU";
        String currencyPair = "UAH/USD";

        BigDecimal purchaseRate = null;
        try {
            purchaseRate = new MonoCurrencyService().getRate(Currency.USD).get("buyUSD");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BigDecimal saleRate = null;
        try {
            saleRate = new MonoCurrencyService().getRate(Currency.USD).get("sellUSD");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String helloText = "";
        if (saleRate == null){
            helloText = MessageFormat
                    .format("{0} курс обміну валют: {1}\n Купівля: {2}\n Продаж:  ⏳ ", bankName, currencyPair, purchaseRate);
        } else {
            helloText = MessageFormat
                    .format("{0} курс обміну валют: {1}\n Купівля: {2}\n Продаж: {3}", bankName, currencyPair, purchaseRate, saleRate);
            //String helloText = service.getInfo(Math.toIntExact(this.chatId)) + "(ukr)";
        }
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
