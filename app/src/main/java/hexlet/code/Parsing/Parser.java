package hexlet.code.Parsing;

import java.util.Map;

public interface Parser {

    Map<String, Object> parse(String content) throws Exception;

}
