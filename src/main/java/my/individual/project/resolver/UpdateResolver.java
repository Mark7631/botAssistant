package my.individual.project.resolver;

import lombok.RequiredArgsConstructor;
import my.individual.project.updatehandler.UpdateHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UpdateResolver {
    private final List<UpdateHandler> updateHandlers;

    public UpdateHandler resolve(Update update) {
        List<UpdateHandler> supportedUpdateHandlers = updateHandlers.stream().filter(updateHandler -> updateHandler.isSupported(update)).toList();
        if (supportedUpdateHandlers.size() != 1) {
            throw new IllegalArgumentException("There is no handler!");
        }
        return supportedUpdateHandlers.get(0);
    }
}
