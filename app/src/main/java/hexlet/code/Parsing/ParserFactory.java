package hexlet.code.Parsing;


public class ParserFactory {

    //выбираем парсер в зав-ти от формата
    public static Parser getParser(String dataFormat) throws Exception {
        return switch (dataFormat.toUpperCase()) {
            case "JSON" -> new JsonParser();
            case "YML", "YAML" -> new YmlParser();
            default -> throw new IllegalArgumentException("Wrong data format:" + dataFormat);
        };
    }
}

