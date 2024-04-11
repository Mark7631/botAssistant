package my.individual.project.repository;

import my.individual.project.enums.BotState;
import my.individual.project.enums.CallbackData;
import my.individual.project.model.UserSession;

public interface UserSessionRepository {
    void updateUserBotStateByChatId(Long chatId, BotState botState);

    BotState getUserBotStateByChatId(Long chatId);

    void updateUserMessageIdByChatId(Long chatId, Integer messageId);

    Integer getUserMessageIdByChatId(Long chatId);

    void updateUserSessionByChatId(Long chatId, UserSession userSession);

    UserSession getUserSessionByChatId(Long chatId);

    void updateUserLastCallbackData(Long chatId, CallbackData callbackData);

    CallbackData getUserLastCallbackData(Long chatId);
}
