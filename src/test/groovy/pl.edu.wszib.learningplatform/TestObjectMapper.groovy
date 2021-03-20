package pl.edu.wszib.learningplatform;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.edu.wszib.learningplatform.config.ObjectMapperConfiguration;

@Component
@RequiredArgsConstructor
class TestObjectMapper {

    private final ObjectMapper objectMapper;

    TestObjectMapper() {
        this.objectMapper = ObjectMapperConfiguration.createObjectMapper();
    }

    String asJson(Object value) throws JsonProcessingException {
        return objectMapper.writeValueAsString(value);
    }

}
