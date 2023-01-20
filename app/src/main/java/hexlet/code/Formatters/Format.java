package hexlet.code.Formatters;

import hexlet.code.Status;

import java.util.Map;

import static hexlet.code.Formatters.JSON.formatToJSON;
import static hexlet.code.Formatters.Plain.formatToPlain;
import static hexlet.code.Formatters.Stylish.formatToStylish;

public class Format {

    public static String formatter(Map<String, Status> result, String formatName) throws Exception {


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
                throw new Exception("Error: unknown format name: " + formatName);
            }
        }

    }
}
