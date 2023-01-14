package hexlet.code.Formatters;

import hexlet.code.Status;

import java.util.Map;

public class Stylish {
    public static String formatToStylish(Map<String, Status> map) {
        StringBuilder result = new StringBuilder("{\n");

        if (map.isEmpty()) {
            return "{}";
        } else {
            for (Map.Entry<String, Status> entry : map.entrySet()) {

                String key = entry.getKey();
                String status = entry.getValue().getStatus();
                Object oldValue = entry.getValue().getOldValue();
                Object newValue = entry.getValue().getNewValue();

                switch (status) {
                    case Status.ADDED ->
                        result.append("  + ").append(key).append(": ").append(newValue).append("\n");
                    case Status.REMOVED ->
                        result.append("  - ").append(key).append(": ").append(oldValue).append("\n");
                    case Status.UNCHANGED ->
                        result.append("    ").append(key).append(": ").append(oldValue).append("\n");
                    case Status.CHANGED -> {
                        result.append("  - ").append(key).append(": ").append(oldValue).append("\n");
                        result.append("  + ").append(key).append(": ").append(newValue).append("\n");
                    }
                    default -> throw new IllegalStateException("Unknown status: " + status);
                }
            }
            result.append("}");
        }
        return result.toString();
    }
}
