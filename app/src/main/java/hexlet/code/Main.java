package hexlet.code;

import hexlet.code.schemas.StringSchema;

public class Main {
    public static void main(String[] args) {
        Validator v = new Validator();

        // Создаем схему валидации и накапливаем правила
        StringSchema schema = v.string();

        // Проверяем значения
        System.out.println(schema.isValid("")); // true
        System.out.println(schema.isValid(null)); // true

        schema.required();

        System.out.println(schema.isValid(null)); // false
        System.out.println(schema.isValid("")); // false
        System.out.println(schema.isValid("what does the fox say")); // true
        System.out.println(schema.isValid("hexlet")); // true

        schema.contains("wh").isValid("what does the fox say"); // true
        schema.contains("what").isValid("what does the fox say"); // true
        schema.contains("whatthe").isValid("what does the fox say"); // false

        // Здесь уже false, так как добавлена еще одна проверка contains("whatthe")
        System.out.println(schema.isValid("what does the fox say")); // false

        var schema1 = v.string();
        schema1.minLength(10).minLength(4).isValid("Hexlet"); // true
    }
}