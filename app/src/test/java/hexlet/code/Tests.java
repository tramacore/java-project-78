package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

public class Tests {
    @Test
    void validatorTest() {
        Validator v = new Validator();
        StringSchema schema = new StringSchema();

        boolean answer = schema.required().minLength(5).contains("let").isValid("Hexlet");
        assertEquals(true, answer);
    }
}
