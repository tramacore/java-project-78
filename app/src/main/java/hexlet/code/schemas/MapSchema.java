package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class MapSchema<T> extends BaseSchema<Map<String, T>> {
    private final List<Predicate<Map<String, T>>> checks = new ArrayList<>();

    public MapSchema<T> required() {
        checks.add(map -> map != null);
        return this;
    }

    public MapSchema<T> sizeof(int size) {
        checks.add(map -> map != null && map.size() >= size);
        return this;
    }

    public <U> MapSchema<T> shape(Map<String, BaseSchema<U>> schemas) {
        checks.add(map -> {
            for (var entry : schemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<U> schema = entry.getValue();
                if (map.containsKey(key)) {
                    T value = map.get(key);
                    if (!schema.isValid(value)) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            return true;
        });
        return this;
    }

    @Override
    protected List<Predicate<Map<String, T>>> getChecks() {
        return checks;
    }

    @Override
    protected Class<Map<String, T>> getType() {
        return (Class<Map<String, T>>) (Class<?>) Map.class;
    }
}