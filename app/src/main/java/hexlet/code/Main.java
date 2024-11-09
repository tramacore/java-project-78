package hexlet.code;

import hexlet.code.schemas.NumberSchema;


public class Main {
    public static void main(String[] args) throws Exception {
        var v = new Validator();

        NumberSchema schema = v.number();

        schema.isValid(5); // true

        schema.isValid(null); // true
        schema.positive().isValid(null); // true

        schema.required();

        schema.isValid(null); // false
        schema.isValid(10); // true

        schema.isValid(-10); // false

        schema.isValid(0); // false

        schema.range(5, 10);

        System.out.println(schema.isValid(5)); // true
        System.out.println(schema.isValid(10));
        System.out.println(schema.isValid(4));
        System.out.println(schema.isValid(11));
        schema.isValid(10); // true
        schema.isValid(4); // false
        schema.isValid(11); // false
    }
}