package ua.goit.telegrambot.telegram;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.goit.telegrambot.telegram.nonCommand.NonCommand;
import ua.goit.telegrambot.telegram.command.StartBotCommand;

@Slf4j
public class TelegramCurrencyBot extends TelegramLongPollingCommandBot {
//    BotUser BotUser;

    public TelegramCurrencyBot() {
        register(new StartBotCommand());

    }

    @Override
    public String getBotUsername() {
        return BotConstants.BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BotConstants.BOT_TOKEN;
    }

    @Override
    public void processNonCommandUpdate(Update update) {

        SendMessage answer = new SendMessage();
        if (update.hasCallbackQuery()) {
            Message msgCallBackQuery = update.getCallbackQuery().getMessage();
            Long chatIdForCallBackQuery = msgCallBackQuery.getChatId();
            String userName = getUserName(msgCallBackQuery);
            String callbackQuery = update.getCallbackQuery().getData();
            log.info("chatId #: " + Long.toString(chatIdForCallBackQuery) + "| rcvd callBackQuery: " + callbackQuery + " | from: " + userName);
            answer = new NonCommand(callbackQuery, chatIdForCallBackQuery, userName).getAnswer();

        }else if (update.hasMessage()){

            Message msgText = update.getMessage();
            Long chatIdForTextMsg = msgText.getChatId();
            String strMsg = msgText.getText();
            String userName = getUserName(msgText);
            log.info("chatId #: " + Long.toString(chatIdForTextMsg) + " | rcvd callBackQuery: " + msgText + " | from: " + userName);
            answer.setChatId(Long.toString(chatIdForTextMsg));
            answer.setText(strMsg);
        }else {
            log.info("wrong request");
            update.getMessage().getChatId();
            String wrongRequest = "Please write '/start'";
            answer.setText(wrongRequest);
            answer.setChatId(Long.toString(update.getMessage().getChatId()));
        }

        try {
            execute(answer);
        } catch (TelegramApiException e) {
            //e.printStackTrace();
            log.error("exception");
        }
    }

    public void sendNotification(Long chatId) {
        SendMessage answer = new SendMessage();
//        answer.setText(BotUser.getInfo());
        answer.setText("The Real Text Comes Here");
        answer.setChatId(chatId.toString());
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            log.error("Haven't send the notifications text");
        }
    }

    private String getUserName(Message msg) {
        User user = msg.getFrom();
        String userName = user.getUserName();
        return (userName != null) ? userName : String.format("%s %s", user.getLastName(), user.getFirstName());
    }

}
