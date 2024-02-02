package hexlet.code;

public abstract class BaseSchema {
    protected boolean isRequired = false;

    public abstract BaseSchema required();

    public abstract boolean isValid(Object value);
}
