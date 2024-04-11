package my.individual.project.updatehandler;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface UpdateHandler {

    void handle(Update update) throws TelegramApiException;

    boolean isSupported(Update update);
}
