package hexlet.code.Parsing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public final class YmlParser implements Parser {

    @Override
    public Map parse(String content) throws Exception {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(content, Map.class);
    }

}
