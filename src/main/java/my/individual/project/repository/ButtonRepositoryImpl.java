package my.individual.project.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import my.individual.project.inlinekeybord.KeyboardButtonCreatorService;
import my.individual.project.model.ButtonResources;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import my.individual.project.exception.TelegramBotException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ButtonRepositoryImpl implements ButtonRepository {
    private final KeyboardButtonCreatorService keyboardButtonCreatorService;

    public List<List<InlineKeyboardButton>> getButtonsById(String id) {
        try {
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            File buttonResourcesRepository = new File("src/main/resources/buttonsRepository.yaml");
            HashMap<String, List<List<ButtonResources>>> buttonIdToButtonResourcesMap = objectMapper.readValue(buttonResourcesRepository, ButtonsFromRepo.class).getAllButtons();
            List<List<ButtonResources>> buttonsResources = buttonIdToButtonResourcesMap.get(id);
            if (buttonsResources != null) {
                List<List<InlineKeyboardButton>> allInlineKeyboardButtons = new ArrayList<>();
                for (int i = 0; i < buttonsResources.size(); i++) {
                    List<ButtonResources> partOfButtonResources = buttonsResources.get(i);
                    List<InlineKeyboardButton> partOfiInlineKeyboardButtons = new ArrayList<>();
                    for (int j = 0; j < partOfButtonResources.size(); j++) {
                        partOfiInlineKeyboardButtons.add(keyboardButtonCreatorService.createButton(partOfButtonResources.get(j).getText(), partOfButtonResources.get(j).getCallbackData()));
                    }
                    allInlineKeyboardButtons.add(partOfiInlineKeyboardButtons);
                }
                return allInlineKeyboardButtons;
            }else {
                return new ArrayList<>();
            }
        } catch (IOException e) {
            new TelegramBotException(e);
            return new ArrayList<>();
        }
    }

    @Getter
    private static class ButtonsFromRepo {
        HashMap<String, List<List<ButtonResources>>> allButtons;
    }
}