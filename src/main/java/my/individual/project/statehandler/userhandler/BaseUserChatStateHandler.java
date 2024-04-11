package my.individual.project.statehandler.userhandler;

import lombok.*;
import my.individual.project.enums.BotState;
import my.individual.project.enums.CallbackData;
import my.individual.project.enums.Command;
import my.individual.project.inlinekeybord.KeyboardCreatorService;
import my.individual.project.service.AnswerSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import my.individual.project.exception.TelegramBotException;
import my.individual.project.repository.TextRepositoryImpl;
import my.individual.project.repository.UserSessionRepository;

@AllArgsConstructor
public abstract class BaseUserChatStateHandler implements UserChatStateHandler {
    protected final AnswerSender answerSender;
    protected final UserSessionRepository userSessionRepository;
    protected final KeyboardCreatorService keyboardCreatorService;
    protected final TextRepositoryImpl textRepositoryImpl;
    @Override
    public void handleCommand(Command command, Message message) {

        switch (command) {
            case START -> {
                long chatId = message.getChatId();
                try {
                    if (command.getLastCallbackData() != null) {
                        userSessionRepository.updateUserLastCallbackData(chatId, command.getLastCallbackData());
                    } else {
                        userSessionRepository.updateUserLastCallbackData(chatId, CallbackData.START);
                    }
                    Integer userMessageIdByChatId = userSessionRepository.getUserMessageIdByChatId(chatId);
                    if (userMessageIdByChatId != null) {
                        answerSender.execute(DeleteMessage.builder()
                                .chatId(chatId)
                                .messageId(userMessageIdByChatId)
                                .build());
                    }
                    Message answerMessage = answerSender.execute(
                            SendMessage.builder()
                            .chatId(chatId)
                            .text(textRepositoryImpl.getTextById(command.getCommandValue().substring(1).toUpperCase()))
                            .replyMarkup(keyboardCreatorService.createInlineKeyboard(command.getCommandValue().substring(1).toUpperCase(), chatId, 0))
                            .build());
                    userSessionRepository.updateUserBotStateByChatId(chatId, BotState.START_STATE);
                    userSessionRepository.updateUserMessageIdByChatId(chatId, answerMessage.getMessageId());
                } catch (TelegramApiException e) {
                    new TelegramBotException(e);
                }
            }
            default ->
                    throw new TelegramBotException("Can't process \"%s\" command!".formatted(command.getCommandValue()));
        }
    }
}
