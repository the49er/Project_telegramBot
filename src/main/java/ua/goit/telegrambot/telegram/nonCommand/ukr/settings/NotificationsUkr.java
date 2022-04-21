package ua.goit.telegrambot.telegram.nonCommand.ukr.settings;
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
public class NotificationsUkr implements GeneralBotCommand {
    String checkout;
    Long chatId;
    String userName;

    public SendMessage getMessage() {
        log.info("open notificationUkr menu");
        String helloText = "Будь ласка, оберіть час нагадування \uD83D\uDD58";

        SendMessage message = new SendMessage();
        message.setText(helloText);
        message.setChatId(Long.toString(this.chatId));

        InlineKeyboardButton nine = InlineKeyboardButton
                .builder()
                .text(this.checkout.equals("9") ? "✅ 9" : "9")
                .callbackData("setNine")
                .build();

        InlineKeyboardButton ten = InlineKeyboardButton
                .builder()
                .text(this.checkout.equals("10") ? "✅ 10" : "10")
                .callbackData("setTen")
                .build();

        InlineKeyboardButton eleven = InlineKeyboardButton
                .builder()
                .text(this.checkout.equals("11") ? "✅ 11" : "11")
                .callbackData("setEleven")
                .build();

        InlineKeyboardButton twelve = InlineKeyboardButton
                .builder()
                .text(this.checkout.equals("12") ? "✅ 12" : "12")
                .callbackData("setTwelve")
                .build();

        InlineKeyboardButton thirteen = InlineKeyboardButton
                .builder()
                .text(this.checkout.equals("13") ? "✅ 13" : "13")
                .callbackData("setThirteen")
                .build();

        InlineKeyboardButton fourteen = InlineKeyboardButton
                .builder()
                .text(this.checkout.equals("14") ? "✅ 14" : "14")
                .callbackData("setFourteen")
                .build();

        InlineKeyboardButton fifteen = InlineKeyboardButton
                .builder()
                .text(this.checkout.equals("15") ? "✅ 15" : "15")
                .callbackData("setFifteen")
                .build();

        InlineKeyboardButton sixteen = InlineKeyboardButton
                .builder()
                .text(this.checkout.equals("16") ? "✅ 16" : "16")
                .callbackData("setSixteen")
                .build();

        InlineKeyboardButton seventeen = InlineKeyboardButton
                .builder()
                .text(this.checkout.equals("17") ? "✅ 17" : "17")
                .callbackData("setSeventeen")
                .build();

        InlineKeyboardButton eighteen = InlineKeyboardButton
                .builder()
                .text(this.checkout.equals("18") ? "✅ 18" : "18")
                .callbackData("setEighteen")
                .build();

        InlineKeyboardButton cancelNotifications = InlineKeyboardButton
                .builder()
                .text("Відмінити нагадування")
                .callbackData("cancelNotificationsUkr")
                .build();

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
