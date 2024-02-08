package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected List<Predicate<T>> checks = new ArrayList<>();

    public abstract BaseSchema<T> required();

    public boolean isValid(T value) {
        return checks.stream()
                .allMatch(p -> p.test(value));
    }
}
