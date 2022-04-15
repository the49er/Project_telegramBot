package ua.goit.telegrambot.telegram.command.eng.mainmenu;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ua.goit.telegrambot.api.dto.Currency;
import ua.goit.telegrambot.ui.PrintCurrencyService;

public class GetInfoCommand extends BotCommand {

    public GetInfoCommand() {
        super("getInfo", "Get info");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        SendMessage message = new SendMessage();
        PrintCurrencyService printCurrencyService = new PrintCurrencyService();
        //message.toString(printCurrencyService.convert(23.3d, Currency.USD)));
    }
}
