package ua.com.javarush.escape_quest.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class ItemLoader {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public HashMap loadGameResourcesFromFile(String filePath) {
        try (InputStream inputStream = getClass().getResourceAsStream(filePath)) {
            byte[] bytesFromFile = inputStream.readAllBytes();

            return objectMapper.readValue(bytesFromFile, HashMap.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}