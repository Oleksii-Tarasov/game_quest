package ua.com.javarush.escape_quest.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ResourceLoaderTest {
    @Test
    void testLoadResourcesFromFile_ShouldThrowException_WhenFilePathIsNull() {
        ResourceLoader resourceLoader = new ResourceLoader();
        assertThrows(NullPointerException.class, () -> resourceLoader.loadResourcesFromFile(null));
    }

    @Test
    void testLoadResourcesFromFile_ShouldThrowException_WhenFilePathIsIncorrect() {
        ResourceLoader resourceLoader = new ResourceLoader();
        assertThrows(NullPointerException.class, () -> resourceLoader.loadResourcesFromFile("/incorrect_filepath.json"));
    }
}
