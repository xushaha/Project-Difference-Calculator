package hexlet.code.Formatters;

import hexlet.code.Status;

import java.util.Map;

import static hexlet.code.Formatters.Plain.formatToPlain;
import static hexlet.code.Formatters.Stylish.formatToStylish;

public class Formatter {

    public static String formatter(Map<String, Status> result, String formatName) {

        if (formatName.equals("plain")) {
            return formatToPlain(result);
        } else {
            return formatToStylish(result);
        }
    }
}
