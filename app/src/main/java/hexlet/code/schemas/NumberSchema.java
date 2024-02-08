package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema<Integer> {
    @Override
    public NumberSchema required() {
        this.checks.add(Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        this.checks.add(n -> n == null || n > 0);
        return this;
    }

    public NumberSchema range(int begin, int end) {
        this.checks.add(n -> n == null || n >= begin && n <= end);
        return this;
    }
}
