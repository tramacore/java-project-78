package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map> {
    private boolean required = false;
    private int minLength = 0;
    private final List<Predicate<Map>> checks = new ArrayList<>();

    public MapSchema required() {
        this.required = true;
        checks.add(map -> map != null);
        return this;
    }

    public MapSchema sizeof(int size) {
        checks.add(map -> map.size() >= size);
        return this;
    }

    @Override
    public List<Predicate<Map>> getChecks() {
        return checks;
    }
}
