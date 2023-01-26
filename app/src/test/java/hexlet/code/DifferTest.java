package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DifferTest {

    public DifferTest() throws IOException {
    }

    private final String expectedStylishResult = Files.readString(Path
        .of("src/test/resources/Expected_Stylish.txt"));
    private final String expectedPlainResult = Files.readString(Path
        .of("src/test/resources/Expected_Plain.txt"));
    private final String expectedJSONResult = Files.readString(Path
        .of("src/test/resources/Expected_JSON.txt"));

    private final String jsonFilePath1 = ("src/test/resources/jsonTest_1.json");
    private final String jsonFilePath2 = ("src/test/resources/jsonTest_2.json");
    private final String yamlFilePath1 = ("src/test/resources/yamlTest_1.yaml");
    private final String yamlFilePath2 = ("src/test/resources/yamlTest_2.yaml");


    @Test
    void testJsonGenerateStylish() throws Exception {
        String actualResult = Differ.generate(jsonFilePath1, jsonFilePath2, "stylish");
        assertThat(actualResult).isEqualTo(expectedStylishResult);
    }

    @Test
    void testJsonGeneratePlain() throws Exception {
        String actualResult = Differ.generate(jsonFilePath1, jsonFilePath2, "plain");
        assertThat(actualResult).isEqualTo(expectedPlainResult);
    }

    @Test
    void testYamlGenerateStylish() throws Exception {
        String actualResult = Differ.generate(yamlFilePath1, yamlFilePath2, "stylish");
        assertThat(actualResult).isEqualTo(expectedStylishResult);
    }

    @Test
    void testYamlGeneratePlain() throws Exception {
        String actualResult = Differ.generate(yamlFilePath1, yamlFilePath2, "plain");
        assertThat(actualResult).isEqualTo(expectedPlainResult);
    }

    @Test
    void testJsonGenerateJSON() throws Exception {
        String actualResult = Differ.generate(jsonFilePath1, jsonFilePath2, "JSON");
        assertThat(actualResult).isEqualTo(expectedJSONResult);
    }

    @Test
    void testYamlGenerateJSON() throws Exception {
        String actualResult = Differ.generate(yamlFilePath1, yamlFilePath2, "JSON");
        assertThat(actualResult).isEqualTo(expectedJSONResult);
    }

    @Test
    void testJsonGenerateDefault() throws Exception {
        String actualResult = Differ.generate(jsonFilePath1, jsonFilePath2);
        assertThat(actualResult).isEqualTo(expectedStylishResult);
    }

    @Test
    void testYamlGenerateDefault() throws Exception {
        String actualResult = Differ.generate(yamlFilePath1, yamlFilePath2);
        assertThat(actualResult).isEqualTo(expectedStylishResult);
    }

}
