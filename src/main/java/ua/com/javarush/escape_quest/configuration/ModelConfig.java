package ua.com.javarush.escape_quest.configuration;

import lombok.Data;

@Data
public class ModelConfig {
    private LocationProperties[] locationProperties;
    private ItemProperties[] itemProperties;
}
