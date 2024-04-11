package my.individual.project.updatehandler;

import lombok.NoArgsConstructor;
import my.individual.project.enums.CallbackData;
import my.individual.project.enums.ChatType;
import my.individual.project.service.AnswerSender;
import my.individual.project.service.AuthenticationService;
import org.glassfish.jersey.Beta;
import my.individual.project.repository.UserSessionRepository;
import my.individual.project.resolver.ChatTypeHandlerResolver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class CallbackQueryUpdateHandler implements UpdateHandler {
    private final ChatTypeHandlerResolver chatTypeHandlerResolver;
    private final AnswerSender answerSender;
    private final UserSessionRepository userSessionRepository;
    private final AuthenticationService authenticationService;

    @Override
    public void handle(Update update) throws TelegramApiException {
        CallbackQuery callbackQuery = update.getCallbackQuery();
        String chatTypeFromUpdateValue = update.getCallbackQuery().getMessage().getChat().getType();
        Long chatId = callbackQuery.getMessage().getChatId();
        ChatType chatTypeFromUpdate = ChatType.findChatTypeByValue(chatTypeFromUpdateValue);

        if (CallbackData.findCallbackDataByValue(callbackQuery.getData()).isNeedAuth()) {
            if (authenticationService.isAuthenticated(chatId)) {
                chatTypeHandlerResolver.resolve(chatTypeFromUpdate).handleCallbackQuery(callbackQuery);
            } else {
                answerSender.execute(AnswerCallbackQuery.builder()
                                       .text("У вас нет доступа до этого раздела! Обратитесь к администратору.")
                                       .callbackQueryId(callbackQuery.getId())
                                       .showAlert(true)
                                       .build());
            }
        } else {
            chatTypeHandlerResolver.resolve(chatTypeFromUpdate).handleCallbackQuery(callbackQuery);
        }
    }

    @Override
    public boolean isSupported(Update update) {
        return update.hasCallbackQuery();
    }

}
