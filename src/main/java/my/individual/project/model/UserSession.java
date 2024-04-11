package my.individual.project.model;

import lombok.*;
import my.individual.project.enums.BotState;
import my.individual.project.enums.CallbackData;

@Data
@Builder(toBuilder = true)
public class UserSession {
    private BotState botState;
    private Long chatId;
    private Integer messageId;
    private CallbackData lastCallbackData;
}
