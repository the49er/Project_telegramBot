package ua.goit.telegrambot.telegram.nonCommand;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface GeneralBotCommand {
    SendMessage getMessage();
}
