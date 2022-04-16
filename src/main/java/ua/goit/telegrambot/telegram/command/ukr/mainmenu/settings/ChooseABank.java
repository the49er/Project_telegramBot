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

public class ChooseABank extends BotCommand {

    public ChooseABank(){
        super("bank","Оберіть банк");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {

        String chooseSettingsMessage = "Будь ласка, оберіть банк ";

        SendMessage message = new SendMessage();
        message.setText(chooseSettingsMessage);
        message.setChatId(Long.toString(chat.getId()));

        InlineKeyboardButton monoBank = InlineKeyboardButton
                .builder()
                .text("Монобанк")
                .callbackData("Обрати Монобанк")
                .build();

        InlineKeyboardButton privatBank = InlineKeyboardButton
                .builder()
                .text("Приватбанк")
                .callbackData("Обрати Приватбанк")
                .build();

        InlineKeyboardButton nbuExRate = InlineKeyboardButton
                .builder()
                .text("НБУ")
                .callbackData("Обрати НБУ")
                .build();



        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(monoBank);

        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(privatBank);

        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        keyboardButtonsRow3.add(nbuExRate);


        List<List<InlineKeyboardButton>> chooseABankKeyboard = new ArrayList<>();
        chooseABankKeyboard.add(keyboardButtonsRow1);
        chooseABankKeyboard.add(keyboardButtonsRow2);
        chooseABankKeyboard.add(keyboardButtonsRow3);


        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        markup.setKeyboard(chooseABankKeyboard);

        message.setReplyMarkup(markup);


        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}




