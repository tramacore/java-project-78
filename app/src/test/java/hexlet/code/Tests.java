package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import hexlet.code.schemas.MapSchema;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

//Не понимаю почему hexlet-check крашится на тестах

public class Tests {
    static Validator v = new Validator();
    StringSchema stringSchema = new StringSchema();
    NumberSchema numberSchema = new NumberSchema();
    MapSchema mapSchema = new MapSchema();

    @BeforeAll
    static void beforeAll() {
        StringSchema stringSchema = v.string();
        NumberSchema numberSchema = v.number();
        MapSchema mapSchema = v.map();
    }

    @Test
    void stringTest() throws Exception {
        boolean actual = stringSchema.required().minLength(5).contains("let").isValid("Hexlet");
        assertTrue(actual);
    }

    @Test
    void stringRequiredTest() throws Exception {
        boolean actual = stringSchema.required().isValid(" ");
        assertFalse(actual);

        boolean actual2 = stringSchema.required().isValid(null);
        assertFalse(actual2);
    }

    @Test
    void stringMinLengthTest() throws Exception {
        boolean actual = stringSchema.minLength(5).isValid("Hexlet");
        assertTrue(actual);
        boolean actual2 = stringSchema.minLength(7).isValid("Hexlet");
        assertFalse(actual2);
    }

    @Test
    void stringContainsTest() throws Exception {
        boolean actual = stringSchema.contains("let").isValid("Hexlet");
        assertTrue(actual);
    }

    @Test
    void numberTest() throws Exception {
        boolean actual = numberSchema.required().positive().range(10, 20).isValid(15);
        assertTrue(actual);
    }

    @Test
    void numberRequiredTest() throws Exception {
        boolean actual = numberSchema.required().isValid(null);
        assertFalse(actual);

        boolean actual2 = numberSchema.required().isValid(1);
        assertTrue(actual2);
    }

    @Test
    void numberPositiveTest() throws Exception {
        boolean actual = numberSchema.positive().isValid(-4);
        assertFalse(actual);

        boolean actual2 = numberSchema.positive().isValid(0);
        assertTrue(actual2);
    }

    @Test
    void numberRangeTest() throws Exception {
        boolean actual = numberSchema.range(3, 6).isValid(2);
        assertFalse(actual);

        boolean actual2 = numberSchema.range(3, 6).isValid(6);
        assertTrue(actual2);
    }

    @Test
    void mapRequiredTest() {
        Map<String, String> map = new HashMap<>();
        boolean actual2 = mapSchema.isValid(null);
        assertTrue(actual2);

        boolean actual = mapSchema.required().isValid(null);
        assertFalse(actual);

        boolean actual1 = mapSchema.required().isValid(map);
        assertTrue(actual1);


    }

    @Test
    void mapSizeOfTest() {
        Map<String, String> trueMap = new HashMap<>(Map.of("sample", "text", "key", "value"));
        boolean actual = mapSchema.sizeof(2).isValid(trueMap);
        assertTrue(actual);

        Map<String, String> falseMap = new HashMap<>(Map.of("sample", "text"));
        boolean actual1 = mapSchema.sizeof(2).isValid(falseMap);
        assertFalse(actual1);
    }

    @Test
    void testShapeValidData() {
        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("firstName", stringSchema.required());
        schemas.put("lastName", stringSchema.required().minLength(2));
        mapSchema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");

        boolean actual = mapSchema.isValid(human1);
        assertTrue(actual);
    }

    @Test
    void testShapeInvalidData() {
        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("firstName", stringSchema.required());
        schemas.put("lastName", stringSchema.required().minLength(2));
        mapSchema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", null);

        boolean actual = mapSchema.isValid(human1);
        assertFalse(actual);

        human1.put("lastname", "B");
        boolean actual1 = mapSchema.isValid(human1);
        assertFalse(actual1);
    }

    @Test
    void testShapeInvalidNumberData() {
        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("firstName", stringSchema.required());
        schemas.put("age", numberSchema.required().range(18, 99));
        mapSchema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("age", null);

        boolean actual = mapSchema.isValid(human1);
        assertFalse(actual);

        human1.put("age", 21);
        boolean actual1 = mapSchema.isValid(human1);
        assertTrue(actual1);
    }
}
