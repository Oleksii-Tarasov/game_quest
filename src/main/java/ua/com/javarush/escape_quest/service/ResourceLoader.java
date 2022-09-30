package ua.com.javarush.escape_quest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.com.javarush.escape_quest.configuration.ModelConfig;

import java.io.IOException;
import java.io.InputStream;

public class ResourceLoader {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ModelConfig loadResourcesFromFile(String filePath) {
        ModelConfig modelConfig = new ModelConfig();

        try (InputStream inputStream = getClass().getResourceAsStream(filePath)) {
            assert inputStream != null;
            byte[] bytesFromFile = inputStream.readAllBytes();
            modelConfig = objectMapper.readValue(bytesFromFile, ModelConfig.class);
        } catch (IOException e) {
//            System.err.println("Couldn't load resources from file " + filePath + e.getMessage());
            e.printStackTrace();
        }

        return modelConfig;
    }
}
