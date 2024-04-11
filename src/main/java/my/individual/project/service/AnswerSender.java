package my.individual.project.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import my.individual.project.config.TelegramBotsProperties;

@Component
public class AnswerSender extends DefaultAbsSender {
    protected AnswerSender(TelegramBotsProperties telegramBotsProperties) {
        super(new DefaultBotOptions(), telegramBotsProperties.token());
    }
}