package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    protected boolean isRequired = false;

    public abstract BaseSchema<T> required();

    public abstract boolean isValid(T value);
}
