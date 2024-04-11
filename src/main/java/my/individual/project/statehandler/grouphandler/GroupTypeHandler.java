package my.individual.project.statehandler.grouphandler;

import my.individual.project.enums.ChatType;
import my.individual.project.enums.Command;
import my.individual.project.exception.TelegramBotException;
import my.individual.project.resolver.GroupChatChatIdHandlerResolver;
import my.individual.project.statehandler.ChatTypeHandler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class GroupTypeHandler implements ChatTypeHandler {
    private final GroupChatChatIdHandlerResolver groupChatChatIdHandlerResolver;

    @Override
    public void handleCallbackQuery(CallbackQuery callbackQuery) {
        Long chatId = callbackQuery.getMessage().getChat().getId();
        try {
            groupChatChatIdHandlerResolver.resolve(chatId).handleCallbackQuery(callbackQuery);
        } catch (TelegramApiException e) {
            new TelegramBotException(e);
        }
    }

    @Override
    public void handleInputMessage(Message message) {
        Long chatId = message.getChat().getId();
        try {
            groupChatChatIdHandlerResolver.resolve(chatId).handleInputMessage(message);
        } catch (TelegramApiException e) {
            new TelegramBotException(e);
        }
    }

    @Override
    public void handleCommand(Command command, Message message) {
        Long chatId = message.getChat().getId();
        try {
            groupChatChatIdHandlerResolver.resolve(chatId).handleCommand(command, message);
        } catch (TelegramApiException e) {
            new TelegramBotException(e);
        }
    }

    @Override
    public ChatType getChatType() {
        return ChatType.SUPERGROUP;
    }
}
