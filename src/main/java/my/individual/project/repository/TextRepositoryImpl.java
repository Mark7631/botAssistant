package my.individual.project.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import my.individual.project.exception.TelegramBotException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TextRepositoryImpl implements TextRepository {

    public String getTextById(String id) {
        try {
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            File textResourcesRepository = new File("src/main/resources/textRepository.yaml");
            Map<String, String> textIdToTextMap = objectMapper.readValue(textResourcesRepository, Map.class);
            return textIdToTextMap.get(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
