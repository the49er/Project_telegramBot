package ua.goit.telegrambot.telegram.command.ukr.mainmenu.settings;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class ChooseСurrenciesUkr extends BotCommand {

    public ChooseСurrenciesUkr(){
        super("choosecurrenciesukr","Яка валюта?");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {

        String chooseSettingsMessage = "Оберіть, будь ласка, потрібну валюту.";

        SendMessage message = new SendMessage();
        message.setText(chooseSettingsMessage);
        message.setChatId(Long.toString(chat.getId()));
        //✅
        InlineKeyboardButton usd = InlineKeyboardButton
                .builder()
                .text("USD")
                .callbackData("Choose USD")
                .build();

        InlineKeyboardButton eur = InlineKeyboardButton
                .builder()
                .text("EUR")
                .callbackData("Choose EUR")
                .build();

        InlineKeyboardButton gbp = InlineKeyboardButton
                .builder()
                .text("GBP")
                .callbackData("Choose GBP")
                .build();

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(usd);

        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(eur);

        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        keyboardButtonsRow3.add(gbp);


        List<List<InlineKeyboardButton>> currencyKeyboard = new ArrayList<>();
        currencyKeyboard.add(keyboardButtonsRow1);
        currencyKeyboard.add(keyboardButtonsRow2);
        currencyKeyboard.add(keyboardButtonsRow3);


        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        markup.setKeyboard(currencyKeyboard);

        message.setReplyMarkup(markup);


        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}




