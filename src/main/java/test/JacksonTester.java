package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JacksonTester {
    public static void main(String[] args) {
        // 创建 ObjectMapper 对象
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "{\"name\":\"Mahesh\", \"age\":21}";

        try {
            // 反序列化 JSON 到对象
            Student student = mapper.readValue(jsonString, Student.class);
            log.info(String.valueOf(student));

            // 序列化对象到 JSON
            String json = mapper.writeValueAsString(student);
            log.info(json);
        } catch (Exception e) {
            log.error("error", e);
        }
    }

}
