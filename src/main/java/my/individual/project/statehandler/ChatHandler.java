package my.individual.project.statehandler;

import my.individual.project.enums.Command;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface ChatHandler {
    void handleInputMessage(Message message) throws TelegramApiException;

    void handleCallbackQuery(CallbackQuery callbackQuery) throws TelegramApiException;

    void handleCommand(Command command, Message message) throws TelegramApiException;
}
