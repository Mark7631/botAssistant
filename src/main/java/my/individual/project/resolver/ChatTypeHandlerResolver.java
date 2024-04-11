package my.individual.project.resolver;

import my.individual.project.enums.ChatType;
import my.individual.project.statehandler.ChatTypeHandler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ChatTypeHandlerResolver {
    private final List<ChatTypeHandler> chatTypeHandlers;

    public ChatTypeHandler resolve(ChatType chatTypeFromUpdate) {
        List<ChatTypeHandler> supportedChatTypeHandlers = chatTypeHandlers.stream().filter(chatTypeHandler -> chatTypeHandler.getChatType().equals(chatTypeFromUpdate)).toList();
        if (supportedChatTypeHandlers.size() != 1) {
            throw new IllegalArgumentException("There is no handler!");
        }
        return supportedChatTypeHandlers.get(0);
    }
}
