package ua.goit.telegrambot.telegram.nonCommand.eng;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ua.goit.telegrambot.settings.UserService;
import ua.goit.telegrambot.telegram.nonCommand.GeneralBotCommand;

@Slf4j
@AllArgsConstructor
public class GetInfoCommand implements GeneralBotCommand {
    Long chatId;
    String userName;


    public SendMessage getMessage() {
        UserService service = UserService.getInstance();

        log.info("receive getInfo response");

        String helloText = service.getInfo(chatId);
        SendMessage message = new SendMessage();//same code
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
