package hexlet.code.Formatters;

import hexlet.code.Status;

import java.util.List;
import java.util.Map;

public class Plain {

    public static String formatToPlain(Map<String, Status> map) {
        StringBuilder result = new StringBuilder();

        if (map.isEmpty()) {
            return "";
        } else {
            for (Map.Entry<String, Status> entry : map.entrySet()) {

                String key = entry.getKey();
                Status value = entry.getValue();

                switch (value.getStatus()) {
                    case Status.ADDED -> {
                        String addedValue = formatter(value.getNewValue());
                        result.append("Property '").append(key).append("' was added with value: ")
                            .append(addedValue).append("\n");}
                    case Status.REMOVED -> {
                        result.append("Property '").append(key).append("' was removed").append("\n");}
                    case Status.CHANGED -> {
                        String removedValue = formatter(value.getOldValue());
                        String addedValue = formatter(value.getNewValue());
                        result.append("Property '").append(key).append("' was updated. From ")
                            .append(removedValue).append(" to ").append(addedValue).append("\n");
                    }
                    case Status.UNCHANGED -> {
                    }
                    default -> throw new IllegalStateException("Unknown status: " + value.getStatus());
                }
            }
        }
        return result.toString();
    }





    public static String formatter(Object value) {

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

