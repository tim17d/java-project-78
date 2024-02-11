package hexlet.code.schemas;

import java.util.Objects;

public final class StringSchema extends BaseSchema<String> {
    public StringSchema() {
        this.checks.add(Objects::nonNull);
    }

    @Override
    public StringSchema required() {
        this.isRequired = true;
        this.checks.add(s -> !s.isEmpty());
        return this;
    }

    public StringSchema minLength(int value) {
        this.checks.add(s -> s.length() >= value);
        return this;
    }

    public StringSchema contains(String str) {
        this.checks.add(s -> s.contains(str));
        return this;
    }
}
