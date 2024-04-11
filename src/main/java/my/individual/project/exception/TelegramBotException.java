package my.individual.project.exception;

public class TelegramBotException extends RuntimeException {
    public TelegramBotException(String message) {
        super(message);
    }

    public TelegramBotException(String message, Throwable cause) {
        super(message, cause);
    }

    public TelegramBotException(Throwable cause) {
        super(cause);
    }
}
