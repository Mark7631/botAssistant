package my.individual.project.resolver;

import my.individual.project.enums.BotState;
import my.individual.project.statehandler.userhandler.UserChatStateHandler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserChatStateHandlerResolver {
    private final List<UserChatStateHandler> userChatStateHandlers;

    public UserChatStateHandler resolve(BotState botState, Long chatId) {
        List<UserChatStateHandler> supportedUserChatStateHandlers = userChatStateHandlers.stream().filter(userHandler -> userHandler.getState(chatId).equals(botState)).toList();
        if (supportedUserChatStateHandlers.size() != 1) {
            throw new IllegalArgumentException("There is no handler! " + botState.name());
        }
        return supportedUserChatStateHandlers.get(0);
    }
}
