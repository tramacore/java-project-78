package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

public class Tests {
    static Validator v = new Validator();
    StringSchema stringSchema = new StringSchema();
    NumberSchema numberSchema = new NumberSchema();

    @BeforeAll
    static void BeforeAll() {
        StringSchema stringSchema = v.string();
        NumberSchema numberSchema = v.number();
    }

    @Test
    void stringTest() throws Exception {
        boolean actual = stringSchema.required().minLength(5).contains("let").isValid("Hexlet");
        assertEquals(true, actual);
    }

    @Test
    void stringRequiredTest() throws Exception {
        boolean actual = stringSchema.required().isValid(" ");
        assertEquals(false, actual);

        boolean actual2 = stringSchema.required().isValid(null);
        assertEquals(false, actual2);
    }

    @Test
    void stringMinLengthTest() throws Exception {
        boolean actual = stringSchema.minLength(5).isValid("Hexlet");
        assertEquals(true, actual);
        boolean actual2 = stringSchema.minLength(7).isValid("Hexlet");
        assertEquals(false, actual2);
    }

    @Test
    void stringContainsTest() throws Exception {
        boolean actual = stringSchema.contains("let").isValid("Hexlet");
        assertEquals(true, actual);
    }

    @Test
    void numberTest() throws Exception {
        boolean actual = numberSchema.required().positive().range(10,20).isValid(15);
        assertEquals(true, actual);
    }
    @Test
    void numberRequiredTest() throws Exception {
        boolean actual = numberSchema.required().isValid(null);
        assertEquals(false, actual);

        boolean actual2 = numberSchema.required().isValid(1);
        assertEquals(true, actual2);
    }

    @Test
    void numberPositiveTest() throws Exception {
        boolean actual =  numberSchema.positive().isValid(-4);
        assertEquals(false, actual);

        boolean actual2 = numberSchema.positive().isValid(0);
        assertEquals(true, actual2);
    }

    @Test
    void numberRangeTest() throws Exception {
        boolean actual = numberSchema.range(3, 6).isValid(2);
        assertEquals(false, actual);

        boolean actual2 = numberSchema.range(3, 6).isValid(6);
        assertEquals(true, actual2);
    }
}
