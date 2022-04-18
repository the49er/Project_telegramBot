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
public class NotificationsUkr implements GeneralBotCommand {
    Long chatId;
    String userName;

    public SendMessage getMessage() {
        String helloText = "Будь-ласка оберіть час повідомлення";
        SendMessage message = new SendMessage();
        message.setText(helloText);
        message.setChatId(Long.toString(this.chatId));
        InlineKeyboardButton nine = InlineKeyboardButton.builder().text("9").callbackData("setNotificationTimeNine").build();
        InlineKeyboardButton ten = InlineKeyboardButton.builder().text("10").callbackData("setNotificationTimeTen").build();
        InlineKeyboardButton eleven = InlineKeyboardButton.builder().text("11").callbackData("setNotificationTimeEleven").build();
        InlineKeyboardButton twelve = InlineKeyboardButton.builder().text("12").callbackData("setNotificationTimeTwelve").build();
        InlineKeyboardButton thirteen = InlineKeyboardButton.builder().text("13").callbackData("setNotificationTimeThirteen").build();
        InlineKeyboardButton fourteen = InlineKeyboardButton.builder().text("14").callbackData("setNotificationTimeFourteen").build();
        InlineKeyboardButton fifteen = InlineKeyboardButton.builder().text("15").callbackData("setNotificationTimeFifteen").build();
        InlineKeyboardButton sixteen = InlineKeyboardButton.builder().text("16").callbackData("setNotificationTimeSixteen").build();
        InlineKeyboardButton seventeen = InlineKeyboardButton.builder().text("17").callbackData("setNotificationTimeSeventeed").build();
        InlineKeyboardButton eighteen = InlineKeyboardButton.builder().text("18").callbackData("setNotificationTimeEighteen").build();
        InlineKeyboardButton cancelNotifications = InlineKeyboardButton.builder().text("Відмінити повідомлення").callbackData("cancelNotifications").build();
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList();
        keyboardButtonsRow1.add(nine);
        keyboardButtonsRow1.add(ten);
        keyboardButtonsRow1.add(eleven);
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList();
        keyboardButtonsRow2.add(twelve);
        keyboardButtonsRow2.add(thirteen);
        keyboardButtonsRow2.add(fourteen);
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList();
        keyboardButtonsRow3.add(fifteen);
        keyboardButtonsRow3.add(sixteen);
        keyboardButtonsRow3.add(seventeen);
        List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList();
        keyboardButtonsRow4.add(eighteen);
        keyboardButtonsRow4.add(cancelNotifications);
        List<List<InlineKeyboardButton>> settingsKeyboard = new ArrayList();
        settingsKeyboard.add(keyboardButtonsRow1);
        settingsKeyboard.add(keyboardButtonsRow2);
        settingsKeyboard.add(keyboardButtonsRow3);
        settingsKeyboard.add(keyboardButtonsRow4);
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(settingsKeyboard);
        message.setReplyMarkup(markup);
        return message;
    }

}
