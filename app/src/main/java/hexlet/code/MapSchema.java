package hexlet.code;

import java.util.Map;

public class MapSchema extends BaseSchema {
    private Integer exactSize;
    private Map<String, BaseSchema> appliedSchemas;

    @Override
    public MapSchema required() {
        this.isRequired = true;
        return this;
    }

    public MapSchema sizeOf(int size) {
        this.exactSize = size;
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        this.appliedSchemas = schemas;
        return this;
    }

    @Override
    public boolean isValid(Object mapObj) {
        try {
            var value = (Map<Object, Object>) mapObj;
            if (this.isRequired) {
                if (value == null) {
                    return false;
                }
            }
            if (this.exactSize != null) {
                if (value.size() != this.exactSize) {
                    return false;
                }
            }
            if (this.appliedSchemas != null) {
                return this.appliedSchemas.entrySet().stream()
                        .allMatch(e -> e.getValue().isValid(value.get(e.getKey())));
            }
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }
}
