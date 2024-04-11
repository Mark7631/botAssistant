package my.individual.project.updatehandler;

import my.individual.project.enums.ChatType;
import my.individual.project.resolver.ChatTypeHandlerResolver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class InputMessageUpdateHandler implements UpdateHandler {
    private final ChatTypeHandlerResolver chatTypeHandlerResolver;

    @Override
    public void handle(Update update) throws TelegramApiException {
        Message message = update.getMessage();
        String chatTypeFromUpdateValue = update.getMessage().getChat().getType();
        ChatType chatTypeFromUpdate = ChatType.findChatTypeByValue(chatTypeFromUpdateValue);

        chatTypeHandlerResolver.resolve(chatTypeFromUpdate).handleInputMessage(message);
    }

    @Override
    public boolean isSupported(Update update) {
        return update.hasMessage() && update.getMessage().hasText() && !update.getMessage().getText().startsWith("/");
    }

}
