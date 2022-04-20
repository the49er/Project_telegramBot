package ua.goit.telegrambot.telegram.nonCommand.eng;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ua.goit.telegrambot.api.dto.Currency;
import ua.goit.telegrambot.api.service.MonoCurrencyService;
import ua.goit.telegrambot.settings.UserService;
import ua.goit.telegrambot.telegram.nonCommand.GeneralBotCommand;

@Slf4j
@AllArgsConstructor
public class GetInfoCommand implements GeneralBotCommand {
    Long chatId;
    String userName;
    //BotUser botUser = new BotUser.getUserByChatId(chatId);


    public SendMessage getMessage() {
        UserService service = UserService.getInstance();

        log.info("receive getInfo response");
        String bankName = "NBU";
        String currencyPair = "UAH/USD";


//        BigDecimal purchaseRate = new MonoCurrencyService().getRate(Currency.USD).get("buyUSD");
//
//        BigDecimal saleRate =  new MonoCurrencyService().getRate(Currency.USD).get("sellUSD");

//        String helloText = "";
//        if (saleRate == null){
//            helloText = MessageFormat
//                    .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: ⏳ ", bankName, currencyPair, purchaseRate);
//        } else {
//            helloText = MessageFormat
//                    .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: {3}", bankName, currencyPair, purchaseRate, saleRate);
//        }

        String helloText = service.getInfo(Math.toIntExact(chatId));
        SendMessage message = new SendMessage();
        message.setText(helloText);
        message.setChatId(Long.toString(this.chatId));

        InlineKeyboardButton getInfo = InlineKeyboardButton
                .builder()
                .text("Get info \uD83D\uDCB1")
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
