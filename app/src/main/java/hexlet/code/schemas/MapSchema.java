package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema<Map<?, ?>> {
    public MapSchema() {
        this.checks.add(Objects::nonNull);
    }

    @Override
    public MapSchema required() {
        this.isRequired = true;
        return this;
    }

    public MapSchema sizeof(int size) {
        this.checks.add(map -> map.size() == size);
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        this.checks.add(map -> schemas.entrySet().stream()
                .allMatch(e -> e.getValue().isValid((T) map.get(e.getKey()))));
        return this;
    }
}
