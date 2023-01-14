package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.nio.file.Files;
import java.nio.file.Path;

public class DifferTest {

    @Test
    void testJsonGenerate1() throws Exception {
        String expectedResult = Files.readString(Path.of("src/test/resources/jsonTestExpected1.json"));
        String filePath1 = ("src/test/resources/jsonTest1_1.json");
        String filePath2 = ("src/test/resources/jsonTest1_2.json");
        String formatName = "stylish";
        String actualResult = Differ.generate(filePath1, filePath2, formatName);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void testJsonGenerate2() throws Exception {
        String expectedResult = Files.readString(Path.of("src/test/resources/jsonTestExpected2.json"));
        String filePath1 = ("src/test/resources/jsonTest2_1.json");
        String filePath2 = ("src/test/resources/jsonTest2_2.json");
        String formatName = "stylish";
        String actualResult = Differ.generate(filePath1, filePath2, formatName);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void testJsonGenerate3() throws Exception {
        String expectedResult = Files.readString(Path.of("src/test/resources/jsonTestExpected3.json"));
        String filePath1 = ("src/test/resources/jsonTest3_1.json");
        String filePath2 = ("src/test/resources/jsonTest3_2.json");
        String formatName = "stylish";
        String actualResult = Differ.generate(filePath1, filePath2, formatName);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void testJsonGenerate3plain() throws Exception {
        String expectedResult = Files.readString(Path.of("src/test/resources/jsonTestExpected4.json"));
        String filePath1 = ("src/test/resources/jsonTest4_1.json");
        String filePath2 = ("src/test/resources/jsonTest4_2.json");
        String formatName = "plain";
        String actualResult = Differ.generate(filePath1, filePath2, formatName);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void testYamlGenerate1() throws Exception {
        String expectedResult = Files.readString(Path.of("src/test/resources/yamlTestExpected1.yaml"));
        String filePath1 = ("src/test/resources/yamlTest1_1.yaml");
        String filePath2 = ("src/test/resources/yamlTest1_2.yaml");
        String formatName = "stylish";
        String actualResult = Differ.generate(filePath1, filePath2, formatName);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void testYamlGenerate2() throws Exception {
        String expectedResult = Files.readString(Path.of("src/test/resources/yamlTestExpected2.yaml"));
        String filePath1 = ("src/test/resources/yamlTest2_1.yaml");
        String filePath2 = ("src/test/resources/yamlTest2_2.yaml");
        String formatName = "stylish";
        String actualResult = Differ.generate(filePath1, filePath2, formatName);
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
