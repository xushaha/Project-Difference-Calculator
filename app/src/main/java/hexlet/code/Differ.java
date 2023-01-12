package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import static hexlet.code.Status.*;


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

        // Читаем файлы
        String content1 = Files.readString(path1);
        String content2 = Files.readString(path2);

        // Строку в мапу
        Map<String, Object> map1 = stringToMap(content1);
        Map<String, Object> map2 = stringToMap(content2);

        // результирующая мапа
        Map<String, Status> result = new TreeMap<>();

        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());

        for (String key: keys) {
            if (!map1.containsKey(key)) {
                // ключ добавили, надо с плюсом
                result.put(key, new Status(ADDED, map2.get(key)));
            } else if (!map2.containsKey(key)) {
                // ключ удалили, надо с минусом
                result.put(key, new Status(REMOVED, map1.get(key)));
            } else if (map1.containsKey(key) && map2.containsKey(key)) {
                // проверяем равны ли значения
                // если равны, то выводим без добавок
                if (map1.get(key).equals(map2.get(key))) {
                    result.put(key, new Status(UNCHANGED, map1.get(key)));
                // если разные, то первый с минусом второй с плюсом
                } else if (!map1.get(key).equals(map2.get(key))) {
                    result.put(key, new Status(CHANGED, map1.get(key), map2.get(key)));
                }
            }
        }

        return toString(result);
    }


    public static Map stringToMap(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, Map.class);
    }

    public static String mapToString(Map<String, Object> map) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(map);
    }

    public static String toString(Map<String, Status> map) {
        StringBuilder result = new StringBuilder("{\n");

        if (map.isEmpty()) {
            return "{}";
        } else {
            for (Map.Entry<String, Status> items : map.entrySet()) {
                Status a = items.getValue();
                String status = a.getStatus();
                Object value = a.getSameValue();
                Object oldValue = a.getOldValue();
                Object newValue = a.getNewValue();
                switch (status) {
                    case ADDED ->
                        result.append("+ ").append(items.getKey()).append(": ").append(value).append("\n");
                    case REMOVED -> result.append("- ").append(items.getKey()).append(": ").append(value).append("\n");
                    case UNCHANGED ->
                        result.append("  ").append(items.getKey()).append(": ").append(value).append("\n");
                    case CHANGED -> {
                        result.append("- ").append(items.getKey()).append(": ").append(oldValue).append("\n");
                        result.append("+ ").append(items.getKey()).append(": ").append(newValue).append("\n");
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + status);
                }
            }
            result.append("}");
        }
        return result.toString();
    }

}
