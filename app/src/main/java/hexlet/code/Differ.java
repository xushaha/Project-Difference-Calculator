package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import static hexlet.code.Parser.parser;

public class Differ {
    static String generate(String filePath1, String filePath2) throws Exception {

/*  Метод generate() должен содержать процесс:
    - чтение файлов в строковые переменные
    - получение входного формата исходя из расширения одного из файлов
    - парсинг данных (в зависимости от входного формата) в мапу
    - построение разницы между данными
    - форматирование разницы в необходимом формате (введён пользователем или дефолтный)*/

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

            for (String key: keys) {
                if (!map1.containsKey(key)) {
                    // ключ добавили, надо с плюсом
                    result.put(key, new Status(Status.ADDED, map2.get(key)));
                } else if (!map2.containsKey(key)) {
                    // ключ удалили, надо с минусом
                    result.put(key, new Status(Status.REMOVED, map1.get(key)));
                } else if (map1.containsKey(key) && map2.containsKey(key)) {
                    // проверяем равны ли значения
                    // если равны, то выводим без добавок
                    if (map1.get(key).equals(map2.get(key))) {
                        result.put(key, new Status(Status.UNCHANGED, map1.get(key)));
                        // если разные, то первый с минусом второй с плюсом
                    } else if (!map1.get(key).equals(map2.get(key))) {
                        result.put(key, new Status(Status.CHANGED, map1.get(key),
                            map2.get(key)));
                    }
                }
            }

            return toString(result);

        } else {
            throw new Exception("Error: data formats must be the same");
        }

    }



    public static String mapToString(Map<String, Object> map) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(map);
    }

    //мапу в строку для вывода на экран
    public static String toString(Map<String, Status> map) {
        StringBuilder result = new StringBuilder("{\n");

        if (map.isEmpty()) {
            return "{}";
        } else {
            for (Map.Entry<String, Status> key : map.entrySet()) {

                String status = key.getValue().getStatus();
                Object value = key.getValue().getSameValue();
                Object oldValue = key.getValue().getOldValue();
                Object newValue = key.getValue().getNewValue();

                switch (status) {
                    case Status.ADDED ->
                        result.append("  + ").append(key.getKey()).append(": ").append(value).append("\n");
                    case Status.REMOVED ->
                        result.append("  - ").append(key.getKey()).append(": ").append(value).append("\n");
                    case Status.UNCHANGED ->
                        result.append("    ").append(key.getKey()).append(": ").append(value).append("\n");
                    case Status.CHANGED -> {
                        result.append("  - ").append(key.getKey()).append(": ").append(oldValue).append("\n");
                        result.append("  + ").append(key.getKey()).append(": ").append(newValue).append("\n");
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + status);
                }
            }
            result.append("}");
        }
        return result.toString();
    }

}
