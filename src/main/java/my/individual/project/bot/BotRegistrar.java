package my.individual.project.bot;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import my.individual.project.exception.TelegramBotException;

@Component
@RequiredArgsConstructor
public class BotRegistrar {
    private final TelegramLongPollingBot telegramLongPollingBot;

    @PostConstruct
    public void registerBot() {

        try {
            new TelegramBotsApi(DefaultBotSession.class).registerBot(telegramLongPollingBot);
        } catch (TelegramApiException e) {
            throw new TelegramBotException(e);
        }
    }
}
