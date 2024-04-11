package my.individual.project.updatehandler;

import my.individual.project.config.TelegramBotsProperties;
import my.individual.project.enums.ChatType;
import my.individual.project.enums.Command;
import my.individual.project.resolver.ChatTypeHandlerResolver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@RequiredArgsConstructor
@Component
public class CommandUpdateHandler implements UpdateHandler {
    private final ChatTypeHandlerResolver chatTypeHandlerResolver;
    private final TelegramBotsProperties telegramBotsProperties;

    @Override
    public void handle(Update update) throws TelegramApiException {
        Message message = update.getMessage();
        String[] commandsParts = message.getText().split("@");
        if(commandsParts.length == 1 || (commandsParts.length == 2 && commandsParts[1].equalsIgnoreCase(telegramBotsProperties.botName()))){
            Command command = Command.findCommandByValue(commandsParts[0]);
            String chatTypeFromUpdateValue = update.getMessage().getChat().getType();
            ChatType chatTypeFromUpdate = ChatType.findChatTypeByValue(chatTypeFromUpdateValue);
            chatTypeHandlerResolver.resolve(chatTypeFromUpdate).handleCommand(command, message);
        }
    }

    @Override
    public boolean isSupported(Update update) {
        return update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().startsWith("/");
    }
}
