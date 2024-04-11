package my.individual.project.bot;

import my.individual.project.config.TelegramBotsProperties;
import my.individual.project.exception.TelegramBotException;
import my.individual.project.resolver.UpdateResolver;
import my.individual.project.updatehandler.UpdateHandler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final UpdateResolver updateResolver;
    private final TelegramBotsProperties telegramBotsProperties;

    public TelegramBot(TelegramBotsProperties telegramBotsProperties,
                       UpdateResolver updateResolver) {
        super(telegramBotsProperties.token());
        this.telegramBotsProperties = telegramBotsProperties;
        this.updateResolver = updateResolver;
    }

    @Override
    public void onUpdateReceived(Update update) {
        UpdateHandler updateHandler = updateResolver.resolve(update);
        try {
            updateHandler.handle(update);
        } catch (TelegramApiException e) {
            throw new TelegramBotException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return telegramBotsProperties.botName();
    }
}
