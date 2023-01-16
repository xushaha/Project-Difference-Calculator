package hexlet.code.Formatters;

import hexlet.code.Status;

import java.util.Map;

import static hexlet.code.Formatters.JSON.formatToJSON;
import static hexlet.code.Formatters.Plain.formatToPlain;
import static hexlet.code.Formatters.Stylish.formatToStylish;

public class Formatter {

    public static String formatter(Map<String, Status> result, String formatName) {


        switch (formatName.toUpperCase()) {
            case "PLAIN" -> {
                return formatToPlain(result);
            }
            case "JSON" -> {
                return formatToJSON(result);
            }
            case "STYLISH" -> {
                return formatToStylish(result);
            }
            default -> {
                return formatToStylish(result);
            }
        }

    }
}
