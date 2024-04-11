package my.individual.project.repository;

import my.individual.project.enums.BotState;
import my.individual.project.enums.CallbackData;
import my.individual.project.model.UserSession;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserSessionDumbMapCacheRepository implements UserSessionRepository {
    Map<Long, UserSession> chatIdToUserSessionMap = new HashMap<>();

    @Override
    public void updateUserBotStateByChatId(Long chatId, BotState botState) {
        getUserSessionByChatId(chatId).setBotState(botState);
    }

    @Override
    public BotState getUserBotStateByChatId(Long chatId) {
        return Optional.ofNullable(getUserSessionByChatId(chatId).getBotState()).orElse(BotState.NO_STATE);
    }

    @Override
    public void updateUserMessageIdByChatId(Long chatId, Integer messageId) {
        getUserSessionByChatId(chatId).setMessageId(messageId);
    }

    @Override
    public Integer getUserMessageIdByChatId(Long chatId) {
        return getUserSessionByChatId(chatId).getMessageId();
    }

    @Override
    public void updateUserSessionByChatId(Long chatId, UserSession newUserSession) {
        chatIdToUserSessionMap.put(chatId, newUserSession);
    }

    @Override
    public UserSession getUserSessionByChatId(Long chatId) {
        UserSession userSession = chatIdToUserSessionMap.get(chatId);
        if (userSession == null) {
            userSession = UserSession.builder()
                    .chatId(chatId)
                    .build();
            updateUserSessionByChatId(chatId, userSession);
        }
        return userSession;
    }

    @Override
    public void updateUserLastCallbackData(Long chatId, CallbackData callbackData) {
        getUserSessionByChatId(chatId).setLastCallbackData(callbackData);
    }

    @Override
    public CallbackData getUserLastCallbackData(Long chatId) {
        return getUserSessionByChatId(chatId).getLastCallbackData();
    }
}
