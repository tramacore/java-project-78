package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class NumberSchema extends BaseSchema<Integer> {
    private boolean required = false;
    private int minLength = 0;
    private final List<Predicate<Integer>> checks = new ArrayList<>();

    public NumberSchema required() {
        this.required = true;
        checks.add(number -> number != null);
        return this;
    }

    public NumberSchema positive() {
        checks.add(number -> number != null && number >= 0);
        return this;
    }

    public NumberSchema range(int minValue, int maxValue) {
        checks.add(number -> number != null && number >= minValue && number <= maxValue);
        return this;
    }

    @Override
    public List<Predicate<Integer>> getChecks() {
        return checks;
    }
}
