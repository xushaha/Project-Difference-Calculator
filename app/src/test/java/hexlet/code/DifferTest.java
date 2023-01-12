package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.nio.file.Files;
import java.nio.file.Path;

public class DifferTest {

    @Test
    void testGenerate1() throws Exception {
        String expectedResult = Files.readString(Path.of("src/test/resources/jsonTestExpected1.txt"));
        String filePath1 = ("src/test/resources/jsonTest1_1.json");
        String filePath2 = ("src/test/resources/jsonTest1_2.json");
        String actualResult = Differ.generate(filePath1, filePath2);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void testGenerate2() throws Exception {
        String expectedResult = Files.readString(Path.of("src/test/resources/1"));
        String filePath1 = ("src/test/resources/jsonTest2_1.json");
        String filePath2 = ("src/test/resources/jsonTest2_2.json");
        String actualResult = Differ.generate(filePath1, filePath2);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

}


