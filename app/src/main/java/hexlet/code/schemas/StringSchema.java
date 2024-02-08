package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    @Override
    public StringSchema required() {
        this.checks.add(s -> s != null && !s.isEmpty());
        return this;
    }

    public StringSchema minLength(int value) {
        this.checks.add(s -> s == null || s.length() >= value);
        return this;
    }

    public StringSchema contains(String str) {
        this.checks.add(s -> s == null || s.contains(str));
        return this;
    }
}
