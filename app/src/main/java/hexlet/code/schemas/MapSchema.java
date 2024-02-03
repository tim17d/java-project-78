package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    private Integer exactSize;
    private Map<String, BaseSchema<?>> appliedSchemas;

    @Override
    public MapSchema required() {
        this.isRequired = true;
        return this;
    }

    public MapSchema sizeof(int size) {
        this.exactSize = size;
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<?>> schemas) {
        this.appliedSchemas = schemas;
        return this;
    }

    @Override
    public boolean isValid(Map<?, ?> map) {
        return requiredCheck(map) && sizeofCheck(map) && shapeCheck(map);
    }

    private boolean requiredCheck(Map<?, ?> map) {
        if (this.isRequired) {
            return map != null;
        }
        return true;
    }

    private boolean sizeofCheck(Map<?, ?> map) {
        if (this.exactSize != null) {
            return map != null && map.size() == this.exactSize;
        }
        return true;
    }

    private <T> boolean shapeCheck(Map<?, T> map) {
        if (this.appliedSchemas != null) {
            return map != null && this.appliedSchemas.entrySet().stream()
                    .allMatch(e -> ((BaseSchema<T>) e.getValue()).isValid(map.get(e.getKey())));
        }
        return true;
    }
}
