package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.util.Map;

public class Parser {

    //выбираем парсер в зав-ти от формата
    public static Map parser(String content, String dataFormat) throws Exception {

        switch (dataFormat.toUpperCase()) {
            case "JSON" -> {
                return parserJSON(content);
            }
            case "YAML", "YML" -> {
                return parserYAML(content);
            }
            default -> throw new Exception("Unknown data format: " + dataFormat);
        }
    }


    //парсинг (строку в мапу) JSON
    public static Map parserJSON(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, Map.class);
    }

    //парсинг (строку в мапу) YAML
    public static Map parserYAML(String content) throws Exception {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(content, Map.class);
    }

}

