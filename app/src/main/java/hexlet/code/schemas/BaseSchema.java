package hexlet.code.schemas;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected List<Predicate<T>> checks;

    public boolean isValid(T value) {
        checks = getChecks();

        for (Predicate<T> check : checks) {
            if (!check.test(value)) {
                return false;
            }
        }
        return true;
    }

    protected abstract List<Predicate<T>> getChecks();
}
