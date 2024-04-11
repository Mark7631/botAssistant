package my.individual.project.statehandler.userhandler;

import my.individual.project.enums.BotState;
import my.individual.project.enums.Command;
import my.individual.project.exception.TelegramBotException;
import my.individual.project.statehandler.ChatHandler;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface UserChatStateHandler extends ChatHandler {

    default void handleInputMessage(Message message) throws TelegramApiException {
        throw new TelegramBotException("Message with data \"%s\" can't be processed on \"%s\" state!".formatted(message.getText(), getState(message.getChatId())));
    }

    default void handleCallbackQuery(CallbackQuery callbackQuery) throws TelegramApiException {
        throw new TelegramBotException("Callback query with data \"%s\" can't be processed on \"%s\" state!".formatted(callbackQuery.getData(), getState(callbackQuery.getMessage().getChatId())));
    }

    default void handleCommand(Command command, Message message) throws TelegramApiException {
        throw new TelegramBotException("Command \"%s\" can't be processed on \"%s\" state!".formatted(command.getCommandValue(), getState(message.getChatId())));
    }

    BotState getState(Long chatId);
}
