package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class StringSchema {
    private boolean required = false;
    private int minLength = 0;
    private final List<Predicate<String>> checks = new ArrayList<>();

    public StringSchema required() {
        this.required = true;
        checks.add(text -> text != null && !text.trim().isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        checks.add(text -> text != null && text.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        checks.add(text -> text != null && text.contains(substring));
        return this;
    }

    public boolean isValid(String text) {
        for (Predicate<String> check : checks) {
            if (!check.test(text)) {
                return false;
            }
        }
        return true;
    }
}