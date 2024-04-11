package my.individual.project.repository;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public interface ButtonRepository {
    List<List<InlineKeyboardButton>> getButtonsById(String id);
}
