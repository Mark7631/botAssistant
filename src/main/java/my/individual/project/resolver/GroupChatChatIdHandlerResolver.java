package my.individual.project.resolver;

import lombok.RequiredArgsConstructor;
import my.individual.project.statehandler.grouphandler.GroupChatChatIdHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GroupChatChatIdHandlerResolver {
    private final List<GroupChatChatIdHandler> groupChatChatIdHandlers;
    public GroupChatChatIdHandler resolve(Long chatId) {
        List<GroupChatChatIdHandler> supportedGroupChatChatIdHandlers = groupChatChatIdHandlers.stream().filter(groupChatChatIdHandler -> groupChatChatIdHandler.getChatId().equals(chatId)).toList();
        if (supportedGroupChatChatIdHandlers.size() != 1) {
            throw new IllegalArgumentException("There is no handler!");
        }
        return supportedGroupChatChatIdHandlers.get(0);
    }
}
