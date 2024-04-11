package my.individual.project.statehandler.userhandler;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import my.individual.project.enums.BotState;
import my.individual.project.enums.ChatType;
import my.individual.project.enums.Command;
import my.individual.project.exception.TelegramBotException;
import my.individual.project.repository.UserSessionRepository;
import my.individual.project.resolver.UserChatStateHandlerResolver;
import my.individual.project.service.AnswerSender;
import my.individual.project.statehandler.ChatTypeHandler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class UserTypeHandler implements ChatTypeHandler {
    private final UserChatStateHandlerResolver userChatStateHandlerResolver;
    private final UserSessionRepository userSessionRepository;
    private final AnswerSender answerSender;

    @Override
    public void handleCallbackQuery(CallbackQuery callbackQuery) throws TelegramApiException {
        Long chatId = callbackQuery.getMessage().getChat().getId();
        BotState userBotStateByChatId = userSessionRepository.getUserBotStateByChatId(chatId);

        Integer usedUserMessageIdByChatId = userSessionRepository.getUserMessageIdByChatId(chatId);
        if (usedUserMessageIdByChatId != null) {
            deleteUnnecessaryUserMessage(callbackQuery, chatId, usedUserMessageIdByChatId);
        }

        userChatStateHandlerResolver.resolve(userBotStateByChatId, chatId).handleCallbackQuery(callbackQuery);
    }

    @Override
    public void handleInputMessage(Message message) throws TelegramApiException {
        Long chatId = message.getChat().getId();
        BotState userBotStateByChatId = userSessionRepository.getUserBotStateByChatId(chatId);
        userChatStateHandlerResolver.resolve(userBotStateByChatId, chatId).handleInputMessage(message);
    }

    @Override
    public void handleCommand(Command command, Message message) throws TelegramApiException {
        Long chatId = message.getChat().getId();
        BotState userBotStateByChatId = userSessionRepository.getUserBotStateByChatId(chatId);
        userChatStateHandlerResolver.resolve(userBotStateByChatId, chatId).handleCommand(command, message);
    }

    private void deleteUnnecessaryUserMessage(CallbackQuery callbackQuery, long chatId, Integer usedUserMessageIdByChatId) {
        if (usedUserMessageIdByChatId > callbackQuery.getMessage().getMessageId()) {
            try {
                answerSender.execute(DeleteMessage.builder()
                        .chatId(chatId)
                        .messageId(callbackQuery.getMessage().getMessageId())
                        .build());
            } catch (TelegramApiException e) {
                new TelegramBotException(e);
            }
        }
        if (usedUserMessageIdByChatId < callbackQuery.getMessage().getMessageId()) {
            try {
                answerSender.execute(DeleteMessage.builder()
                        .chatId(chatId)
                        .messageId(usedUserMessageIdByChatId)
                        .build());
            } catch (TelegramApiException e) {
                new TelegramBotException(e);
            }
            userSessionRepository.updateUserMessageIdByChatId(chatId, callbackQuery.getMessage().getMessageId());
        }
    }

    @Override
    public ChatType getChatType() {
        return ChatType.PRIVATE;
    }
}
