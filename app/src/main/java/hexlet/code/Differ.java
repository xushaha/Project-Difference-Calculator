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
    static String generate(String filePath1, String filePath2, String formatName) throws Exception {

        // Формируем путь абсолютный путь, если filePath будет содержать относительный путь,
        // то мы всегда будет работать с абсолютным
        Path path1 = Path.of(filePath1).toAbsolutePath().normalize();
        Path path2 = Path.of(filePath2).toAbsolutePath().normalize();

        // Проверяем существование файла
        if (!Files.exists(path1)) {
            throw new Exception("File '" + path1 + "' does not exist");
        }
        if (!Files.exists(path2)) {
            throw new Exception("File '" + path2 + "' does not exist");
        }

        //определяем формат файла
        String dataFormat1 = filePath1.substring(filePath1.lastIndexOf('.') + 1);
        String dataFormat2 = filePath2.substring(filePath2.lastIndexOf('.') + 1);

        if (dataFormat1.equals(dataFormat2)) {

            // Читаем файлы в строку
            String content1 = Files.readString(path1);
            String content2 = Files.readString(path2);

            // Строку в мапу (парсинг)
            Map<String, Object> map1 = parser(content1, dataFormat1);
            Map<String, Object> map2 = parser(content2, dataFormat1);

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
                        result.put(key, new Status(Status.UNCHANGED, String.valueOf((map1.get(key)))));

                    } else if (!(String.valueOf(map1.get(key)).equals(String.valueOf((map2.get(key)))))) {
                        result.put(key, new Status(Status.CHANGED, String.valueOf(map1.get(key)),
                            String.valueOf(map2.get(key))));
                    }
                }
            }

            return formatter(result,formatName);


        } else {
            throw new Exception("Error: data formats must be the same");
        }
    }
}
