package my.individual.project.inlinekeybord;

import lombok.RequiredArgsConstructor;
import my.individual.project.enums.CallbackData;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import my.individual.project.repository.ButtonRepositoryImpl;
import my.individual.project.repository.UserSessionRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class KeyboardCreatorService {
    private final UserSessionRepository userSessionRepository;
    private final ButtonRepositoryImpl buttonRepositoryImpl;
    private final KeyboardButtonCreatorService keyboardButtonCreatorService;
    public InlineKeyboardMarkup createInlineKeyboard(String buttonsId, Long chatId, int level) {
        List<List<InlineKeyboardButton>> buttons = buttonRepositoryImpl.getButtonsById(buttonsId);
        if (buttons == null){
            buttons = new ArrayList<>();
        }
        CallbackData lastCallbackData = userSessionRepository.getUserLastCallbackData(chatId);
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        if (userSessionRepository.getUserLastCallbackData(chatId) == null) {
            markupInline.setKeyboard(buttons);
            return markupInline;
        }
        if (level != 0) {
            List<InlineKeyboardButton> listForButtonBack = new ArrayList<>();
            InlineKeyboardButton buttonBack = keyboardButtonCreatorService.createButton("Назад", lastCallbackData.getCallbackDataValue());
            listForButtonBack.add(buttonBack);
            buttons.add(listForButtonBack);
            if (level != 1) {
                List<InlineKeyboardButton> listForButtonStart = new ArrayList<>();
                InlineKeyboardButton buttonStart = keyboardButtonCreatorService.createButton("На главную", CallbackData.START.getCallbackDataValue());
                listForButtonStart.add(buttonStart);
                buttons.add(listForButtonStart);
            }
        }
        markupInline.setKeyboard(buttons);
        return markupInline;
    }
}
