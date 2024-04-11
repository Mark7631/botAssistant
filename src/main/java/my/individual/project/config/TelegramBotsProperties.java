package my.individual.project.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationProperties(prefix = "telegram.bot")
@ConfigurationPropertiesScan
public record TelegramBotsProperties(
        String token,
        String botName,
        Long zniGroupId
) {}
