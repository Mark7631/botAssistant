package my.individual.project.statehandler;

import my.individual.project.enums.ChatType;
import my.individual.project.enums.Command;
import my.individual.project.exception.TelegramBotException;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface ChatTypeHandler {
    default void handleInputMessage(Message message) throws TelegramApiException {
        throw new TelegramBotException("Message with data \"%s\" can't be processed by \"%s\"".formatted(message.getText(),this.getClass().getSimpleName()));
    }

    default void handleCallbackQuery(CallbackQuery callbackQuery) throws TelegramApiException{
        throw new TelegramBotException("CallbackQuery with data \"%s\" can't be processed by \"%s\"".formatted(callbackQuery.getMessage().getText(),this.getClass().getSimpleName()));
    }

    default void handleCommand(Command command, Message message) throws TelegramApiException{
        throw new TelegramBotException("Message with data \"%s\" can't be processed by \"%s\"".formatted(message.getText(),this.getClass().getSimpleName()));
    }

    ChatType getChatType();
}
