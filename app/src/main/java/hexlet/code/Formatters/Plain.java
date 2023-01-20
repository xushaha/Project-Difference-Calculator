package hexlet.code.Formatters;

import hexlet.code.Status;

import java.util.Formatter;
import java.util.List;
import java.util.Map;

public class Plain {

    public static String formatToPlain(Map<String, Status> map) {
        if (map.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        Formatter ftm = new Formatter(result);

        for (Map.Entry<String, Status> entry : map.entrySet()) {

            String key = entry.getKey();
            Status value = entry.getValue();

            switch (value.getStatus()) {
                case Status.ADDED -> {
                    String addedValue = normalize(value.getNewValue());
                    ftm.format("Property '%s' was added with value: %s%n", key, addedValue);
                }
                case Status.REMOVED -> ftm.format("Property '%s' was removed%n", key);
                case Status.CHANGED -> {
                    String removedValue = normalize(value.getOldValue());
                    String addedValue = normalize(value.getNewValue());
                    ftm.format("Property '%s' was updated. From %s to %s%n", key, removedValue, addedValue);
                }
                case Status.UNCHANGED -> {
                }
                default -> throw new IllegalStateException("Unknown status: " + value.getStatus());
            }
        }
        return result.toString().trim();
    }


    public static String normalize(Object value) {

        if (value == null) {
            return "null";
        }

        if (value instanceof List || value instanceof Map) {
            return "[complex value]";
        }

        if (value instanceof String) {
            return "'" + value + "'";
        }

        return value.toString();

    }
}

