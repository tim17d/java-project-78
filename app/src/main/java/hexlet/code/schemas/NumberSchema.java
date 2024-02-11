package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema() {
        this.checks.add(Objects::nonNull);
    }

    @Override
    public NumberSchema required() {
        this.isRequired = true;
        return this;
    }

    public NumberSchema positive() {
        this.checks.add(n -> n > 0);
        return this;
    }

    public NumberSchema range(int begin, int end) {
        this.checks.add(n -> n >= begin && n <= end);
        return this;
    }
}
