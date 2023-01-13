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
        String actualResult = Differ.generate(filePath1, filePath2);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void testJsonGenerate2() throws Exception {
        String expectedResult = Files.readString(Path.of("src/test/resources/jsonTestExpected2.json"));
        String filePath1 = ("src/test/resources/jsonTest2_1.json");
        String filePath2 = ("src/test/resources/jsonTest2_2.json");
        String actualResult = Differ.generate(filePath1, filePath2);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void testYamlGenerate1() throws Exception {
        String expectedResult = Files.readString(Path.of("src/test/resources/yamlTestExpected1.yaml"));
        String filePath1 = ("src/test/resources/yamlTest1_1.yaml");
        String filePath2 = ("src/test/resources/yamlTest1_2.yaml");
        String actualResult = Differ.generate(filePath1, filePath2);
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
