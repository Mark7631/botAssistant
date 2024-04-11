package my.individual.project.statehandler.userhandler;

import my.individual.project.enums.BotState;
import my.individual.project.enums.CallbackData;
import my.individual.project.inlinekeybord.KeyboardCreatorService;
import my.individual.project.repository.TextRepositoryImpl;
import my.individual.project.service.AnswerSender;
import my.individual.project.exception.TelegramBotException;
import my.individual.project.repository.UserSessionRepository;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class UserHandler extends BaseUserChatStateHandler {
    public UserHandler(AnswerSender answerSender, UserSessionRepository userSessionRepository, KeyboardCreatorService keyboardCreatorService, TextRepositoryImpl textRepositoryImpl) {
        super(answerSender, userSessionRepository, keyboardCreatorService, textRepositoryImpl);
    }

    @Override
    public void handleCallbackQuery(CallbackQuery callbackQuery) {
        long chatId = callbackQuery.getFrom().getId();
        CallbackData callbackData = CallbackData.findCallbackDataByValue(callbackQuery.getData());
        CallbackData lastCallbackData = CallbackData.START;
        try {
            lastCallbackData = callbackData.getLastCallbackData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            userSessionRepository.updateUserLastCallbackData(chatId, lastCallbackData);

            Integer userMessageIdByChatId = userSessionRepository.getUserMessageIdByChatId(chatId);
            EditMessageText editMessageText = EditMessageText.builder()
                    .chatId(chatId)
                    .messageId(userMessageIdByChatId)
                    .text(textRepositoryImpl.getTextById(callbackData.name()))
                    .replyMarkup(keyboardCreatorService.createInlineKeyboard(callbackData.name(), chatId, callbackData.getState().getLevelInTreeOfMessage()))
                    .build();
            answerSender.execute(editMessageText);
            userSessionRepository.updateUserBotStateByChatId(chatId, callbackData.getState());
        } catch (TelegramApiException e) {
            new TelegramBotException(e);
        }
    }

    @Override
    public BotState getState(Long chatId) {
        return userSessionRepository.getUserBotStateByChatId(chatId);
    }
}
