package my.individual.project.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Command {
    START("/start", "start command", BotState.START_STATE, null);

    private final String commandValue;
    private final String commandDescription;
    private final BotState state;
    private final CallbackData lastCallbackData;

    public static Command findCommandByValue(String commandValue) {
        List<Command> commandList = Arrays.stream(Command.values())
                .filter(command -> command.getCommandValue().equals(commandValue))
                .toList();
        if (commandList.size() != 1) {
            throw new IllegalStateException("There is no command for \"%s\" value!".formatted(commandValue));
        }
        return commandList.get(0);
    }
}
