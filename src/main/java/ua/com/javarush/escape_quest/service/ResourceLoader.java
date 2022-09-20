package ua.com.javarush.escape_quest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.com.javarush.escape_quest.configuration.ModelConfig;

import java.io.IOException;
import java.io.InputStream;

public class ResourceLoader {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ModelConfig loadResourcesFromFile(String filePath) {
        try (InputStream inputStream = getClass().getResourceAsStream(filePath)) {
            byte[] bytesFromFile = inputStream.readAllBytes();
            return objectMapper.readValue(bytesFromFile, ModelConfig.class);
        } catch (NullPointerException | IOException e) {
            throw new NullPointerException("Couldn't load resources from file " + filePath);
        }
    }
}
