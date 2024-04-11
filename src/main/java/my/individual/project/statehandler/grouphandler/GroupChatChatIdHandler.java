package my.individual.project.statehandler.grouphandler;

import my.individual.project.enums.Command;
import my.individual.project.exception.TelegramBotException;
import my.individual.project.statehandler.ChatHandler;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface GroupChatChatIdHandler extends ChatHandler {
    default void handleInputMessage(Message message) throws TelegramApiException {
        throw new TelegramBotException("Message with data \"%s\" can't be processed on \"%d\" chat id!".formatted(message.getText(), getChatId()));
    }

    default void handleCallbackQuery(CallbackQuery callbackQuery) throws TelegramApiException {
        throw new TelegramBotException("Callback query with data \"%s\" can't be processed on \"%d\" chat id!".formatted(callbackQuery.getData(), getChatId()));
    }

    default void handleCommand(Command command, Message message) throws TelegramApiException {
        throw new TelegramBotException("Command \"%s\" can't be processed on \"%d\" chat id!".formatted(command.getCommandValue(), getChatId()));
    }
    Long getChatId();
}
