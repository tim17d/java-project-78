package hexlet.code;

public abstract class BaseSchema {
    protected boolean isRequired = false;

    public abstract boolean isValid(Object value);
}
