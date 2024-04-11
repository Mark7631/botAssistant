package my.individual.project.inlinekeybord;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Component
public class KeyboardButtonCreatorService {
    public InlineKeyboardButton createButton(String buttonText, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton(buttonText);
        button.setCallbackData(callbackData);
        return button;
    }
}
