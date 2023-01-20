package hexlet.code.Parsing;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public final class JsonParser implements Parser {

    @Override
    public Map parse(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, Map.class);
    }


}
