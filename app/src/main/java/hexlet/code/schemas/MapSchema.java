package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    @Override
    public MapSchema required() {
        this.checks.add(Objects::nonNull);
        return this;
    }

    public MapSchema sizeof(int size) {
        this.checks.add(map -> map == null || map.size() == size);
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        this.checks.add(map -> map == null || schemas.entrySet().stream()
                .allMatch(e -> e.getValue().isValid((T) map.get(e.getKey()))));
        return this;
    }
}
