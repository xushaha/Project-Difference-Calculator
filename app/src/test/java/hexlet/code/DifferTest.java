package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.nio.file.Files;
import java.nio.file.Path;

public class DifferTest {

    @Test
    void testJsonGenerateStylish() throws Exception {
        String expectedResult = Files.readString(Path.of("src/test/resources/Expected_Stylish.txt"));
        String filePath1 = ("src/test/resources/jsonTest_1.json");
        String filePath2 = ("src/test/resources/jsonTest_2.json");
        String formatName = "stylish";
        String actualResult = Differ.generate(filePath1, filePath2, formatName);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void testJsonGeneratePlain() throws Exception {
        String expectedResult = Files.readString(Path.of("src/test/resources/Expected_Plain.txt"));
        String filePath1 = ("src/test/resources/jsonTest_1.json");
        String filePath2 = ("src/test/resources/jsonTest_2.json");
        String formatName = "plain";
        String actualResult = Differ.generate(filePath1, filePath2, formatName);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void testYamlGenerateStylish() throws Exception {
        String expectedResult = Files.readString(Path.of("src/test/resources/Expected_Stylish.txt"));
        String filePath1 = ("src/test/resources/yamlTest_1.yaml");
        String filePath2 = ("src/test/resources/yamlTest_2.yaml");
        String formatName = "stylish";
        String actualResult = Differ.generate(filePath1, filePath2, formatName);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void testYamlGeneratePlain() throws Exception {
        String expectedResult = Files.readString(Path.of("src/test/resources/Expected_Plain.txt"));
        String filePath1 = ("src/test/resources/yamlTest_1.yaml");
        String filePath2 = ("src/test/resources/yamlTest_2.yaml");
        String formatName = "plain";
        String actualResult = Differ.generate(filePath1, filePath2, formatName);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void testJsonGenerateJSON() throws Exception {
        String expectedResult = Files.readString(Path.of("src/test/resources/Expected_JSON.txt"));
        String filePath1 = ("src/test/resources/jsonTest_1.json");
        String filePath2 = ("src/test/resources/jsonTest_2.json");
        String formatName = "JSON";
        String actualResult = Differ.generate(filePath1, filePath2, formatName);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void testYamlGenerateJSON() throws Exception {
        String expectedResult = Files.readString(Path.of("src/test/resources/Expected_JSON.txt"));
        String filePath1 = ("src/test/resources/yamlTest_1.yaml");
        String filePath2 = ("src/test/resources/yamlTest_2.yaml");
        String formatName = "JSON";
        String actualResult = Differ.generate(filePath1, filePath2, formatName);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void testJsonGenerateDefault() throws Exception {
        String expectedResult = Files.readString(Path.of("src/test/resources/Expected_Stylish.txt"));
        String filePath1 = ("src/test/resources/jsonTest_1.json");
        String filePath2 = ("src/test/resources/jsonTest_2.json");
        String actualResult = Differ.generate(filePath1, filePath2);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void testYamlGenerateDefault() throws Exception {
        String expectedResult = Files.readString(Path.of("src/test/resources/Expected_Stylish.txt"));
        String filePath1 = ("src/test/resources/yamlTest_1.yaml");
        String filePath2 = ("src/test/resources/yamlTest_2.yaml");
        String actualResult = Differ.generate(filePath1, filePath2);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

}
