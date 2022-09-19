package ua.goit.telegrambot.telegram.command;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.goit.telegrambot.settings.UserService;

import java.util.Collections;
import java.util.List;
@Slf4j
public class StartBotCommand extends BotCommand {

    public StartBotCommand() {
        super("start", "Start the Bot");

    }

    UserService service = UserService.getInstance();

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        log.info("/start");
        String helloText = "Please select your language.\nБудь ласка, оберіть мову.";
        service.createUser(chat.getId());

        SendMessage message = new SendMessage();
        message.setText(helloText);
        message.setChatId(Long.toString(chat.getId()));

        InlineKeyboardButton chooseENG = InlineKeyboardButton
                .builder()
                .text("English\uD83C\uDDFA\uD83C\uDDF8")
                .callbackData("english")
                .build();

        InlineKeyboardButton chooseUKR = InlineKeyboardButton
                .builder()
                .text("Українська\uD83C\uDDFA\uD83C\uDDE6")
                .callbackData("ukrainian")
                .build();

        InlineKeyboardMarkup keyboard = InlineKeyboardMarkup
                .builder()
                .keyboard(Collections.singleton(List.of(chooseENG, chooseUKR)))
                .build();

        message.setReplyMarkup(keyboard);

        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}