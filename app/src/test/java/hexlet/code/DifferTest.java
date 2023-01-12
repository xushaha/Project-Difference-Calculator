package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.nio.file.Files;
import java.nio.file.Path;

public class DifferTest {

    @Test
    void testGenerate() throws Exception {
        String expectedResult = Files.readString(Path.of("src/test/resources/resultPath"));
        String filePath1 = ("src/test/resources/filePath1.json");
        String filePath2 = ("src/test/resources/filePath2.json");
        String actualResult = Differ.generate(filePath1, filePath2);
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}


