package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Objects;

public class DiffBuilder {

    public static Map<String, Status> genDiff(Map<String, Object> map1, Map<String, Object> map2) {

        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());

        Map<String, Status> result = new TreeMap<>();

        for (String key : keys) {

            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            if (!map1.containsKey(key)) {
                result.put(key, new Status(Status.ADDED, value2));

            } else if (!map2.containsKey(key)) {
                result.put(key, new Status(Status.REMOVED, value1));

            } else if (map1.containsKey(key) && map2.containsKey(key)) {
                if (isEqual(value1, value2)) {
                    result.put(key, new Status(Status.UNCHANGED, (value1)));

                } else {
                    result.put(key, new Status(Status.CHANGED, value1,
                        value2));
                }
            }
        }
        return result;
    }

    public static boolean isEqual(Object val1, Object val2) {

        if (!(val1 == null && val2 == null) && !(Objects.equals(val1, val2))) {
            return false;
        } else if (!(val1 == null && val2 == null) && val1.equals(val2)) {
            return true;
        }
        return false;
    }
}
