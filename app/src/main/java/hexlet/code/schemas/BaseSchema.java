package hexlet.code.schemas;

import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected List<Predicate<T>> checks;

    public boolean isValid(Object value) {
        if (value != null && !getType().isInstance(value)) {
            throw new IllegalArgumentException("Invalid type: " + value.getClass().getName());
        }

        T typedValue = getType().cast(value);
        checks = getChecks();

        for (Predicate<T> check : checks) {
            if (!check.test(typedValue)) {
                return false;
            }
        }
        return true;
    }

    protected abstract List<Predicate<T>> getChecks();

    protected abstract Class<T> getType();
}
