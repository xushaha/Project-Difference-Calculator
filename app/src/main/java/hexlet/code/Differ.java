package hexlet.code;

import hexlet.code.Parsing.ParserFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static hexlet.code.DiffBuilder.genDiff;
import static hexlet.code.Formatters.Format.formatResult;

public class Differ {

    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {

        // получаем мапу (парсинг)
        Map<String, Object> map1 = getData(filePath1);
        Map<String, Object> map2 = getData(filePath2);

        return formatResult(genDiff(map1, map2), formatName);

    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return Differ.generate(filepath1, filepath2, "stylish");
    }

    public static Map getData(String filePath) throws Exception {
        // получаем абсолютный путь
        Path path = Path.of(filePath).toAbsolutePath().normalize();
        // Проверяем существование файла
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        //определяем формат файла
        String dataFormat = filePath.substring(filePath.lastIndexOf('.') + 1);
        // Читаем файлы в строку
        String content = Files.readString(path);

        // Строку в мапу (парсинг)
        var parser = ParserFactory.getParser(dataFormat);
        return parser.parse(content);

    }
}
