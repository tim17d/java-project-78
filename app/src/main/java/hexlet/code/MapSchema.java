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
        Map<?, ?> map;
        try {
            map = (Map<?, ?>) mapObj;
        } catch (ClassCastException e) {
            return false;
        }
        return requiredCheck(map) && sizeOfCheck(map) && shapeCheck(map);
    }

    private boolean requiredCheck(Map<?, ?> map) {
        if (this.isRequired) {
            return map != null;
        }
        return true;
    }

    private boolean sizeOfCheck(Map<?, ?> map) {
        if (this.exactSize != null) {
            return map != null && map.size() == this.exactSize;
        }
        return true;
    }

    private boolean shapeCheck(Map<?, ?> map) {
        if (this.appliedSchemas != null) {
            return map != null && this.appliedSchemas.entrySet().stream()
                    .allMatch(e -> e.getValue().isValid(map.get(e.getKey())));
        }
        return true;
    }
}
