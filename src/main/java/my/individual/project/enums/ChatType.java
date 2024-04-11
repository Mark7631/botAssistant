package my.individual.project.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Getter
public enum ChatType {
    PRIVATE("private"),
    SUPERGROUP("supergroup");

    private final String value;

    public static ChatType findChatTypeByValue(String chatTypeValue) {
        List<ChatType> chatTypeList = Arrays.stream(ChatType.values())
                                              .filter(chatType -> chatType.getValue().equals(chatTypeValue))
                                              .toList();
        if (chatTypeList.size() != 1) {
            throw new IllegalStateException("There is no chat type for \"%s\" value!".formatted(chatTypeValue));
        }
        return chatTypeList.get(0);
    }
}
