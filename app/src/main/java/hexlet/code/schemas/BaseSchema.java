package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected boolean isRequired = false;
    protected List<Predicate<T>> checks = new ArrayList<>();

    public abstract BaseSchema<T> required();

    public final boolean isValid(T value) {
        if (!this.checks.get(0).test(value) && !this.isRequired) {
            return true;
        }
        return this.checks.stream()
                .allMatch(p -> p.test(value));
    }
}
