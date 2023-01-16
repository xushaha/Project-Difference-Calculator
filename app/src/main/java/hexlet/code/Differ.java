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

            // результирующая мапа
        Map<String, Status> result = new TreeMap<>();

        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());

        for (String key : keys) {
            if (!map1.containsKey(key)) {
                result.put(key, new Status(Status.ADDED, map2.get(key)));

            } else if (!map2.containsKey(key)) {
                result.put(key, new Status(Status.REMOVED, map1.get(key)));

            } else if (map1.containsKey(key) && map2.containsKey(key)) {
                if (String.valueOf(map1.get(key)).equals(String.valueOf(map2.get(key)))) {
                    result.put(key, new Status(Status.UNCHANGED, (map1.get(key))));

                } else if (!(String.valueOf(map1.get(key)).equals(String.valueOf((map2.get(key)))))) {
                    result.put(key, new Status(Status.CHANGED, map1.get(key),
                        map2.get(key)));
                }
            }
        }

        return formatter(result, formatName);

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
        return (Map<String, Object>) parser(content1, dataFormat);
    }

}

