package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<String, Object>> {
    private final List<Predicate<Map<String, Object>>> checks = new ArrayList<>();

    public MapSchema required() {
        checks.add(map -> map != null);
        return this;
    }

    public MapSchema sizeof(int size) {
        checks.add(map -> map != null && map.size() >= size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<?>> schemas) {
        checks.add(map -> {
            for (var entry : schemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<?> schema = entry.getValue();
                if (map.containsKey(key)) {
                    Object value = map.get(key);
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
    protected List<Predicate<Map<String, Object>>> getChecks() {
        return checks;
    }

    @Override
    protected Class<Map<String, Object>> getType() {
        return (Class<Map<String, Object>>) (Class<?>) Map.class;
    }
}
