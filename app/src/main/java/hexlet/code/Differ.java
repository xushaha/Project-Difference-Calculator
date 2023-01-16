package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static hexlet.code.Formatters.Formatter.formatter;
import static hexlet.code.Parser.parser;

public class Differ {

    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {

        // получаем мапу (парсинг)
        Map<String, Object> map1 = generateMapFromFile(filePath1);
        Map<String, Object> map2 = generateMapFromFile(filePath2);

        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());

        // результирующая мапа
        Map<String, Status> result = new TreeMap<>();

        for (String key : keys) {

            Object value1 = map1.get(key);
            Object value2 = map2.get(key);
            String value1ToString = String.valueOf(value1);
            String value2ToString = String.valueOf(value2);

            if (!map1.containsKey(key)) {
                result.put(key, new Status(Status.ADDED, value2));

            } else if (!map2.containsKey(key)) {
                result.put(key, new Status(Status.REMOVED, value1));

            } else if (map1.containsKey(key) && map2.containsKey(key)) {
                if (value1ToString.equals(value2ToString)) {
                    result.put(key, new Status(Status.UNCHANGED, (value1)));

                } else {
                    result.put(key, new Status(Status.CHANGED, value1,
                        value2));
                }
            }
        }

        return formatter(result, formatName);

    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return Differ.generate(filepath1, filepath2, "stylish");
    }

    public static Map<String, Object> generateMapFromFile(String filePath) throws Exception {
        // получаем абсолютный путь
        Path path = Path.of(filePath).toAbsolutePath().normalize();

        // Проверяем существование файла
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }

        //определяем формат файла
        String dataFormat = filePath.substring(filePath.lastIndexOf('.') + 1);

        // Читаем файлы в строку
        String content1 = Files.readString(path);

        // Строку в мапу (парсинг)
        return parser(content1, dataFormat);
    }

}

