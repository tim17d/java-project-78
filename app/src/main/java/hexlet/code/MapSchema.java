package hexlet.code;

import java.util.Map;

public class MapSchema extends BaseSchema {
    private Integer exactSize;

    @Override
    public MapSchema required() {
        this.isRequired = true;
        return this;
    }

    public MapSchema sizeOf(int size) {
        this.exactSize = size;
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
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }
}
