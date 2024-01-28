package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringSchemaTest {
    private StringSchema stringSchema;

    @BeforeEach
    public void init() {
        stringSchema = new StringSchema();
    }

    @Test
    public void testEmptyValidationIsValid() {
        assertThat(stringSchema.isValid(null)).isTrue();
    }

    @Test
    public void testRequiredIsValid() {
        stringSchema.required();
        assertThat(stringSchema.isValid(null)).isFalse();
        assertThat(stringSchema.isValid("")).isFalse();
        assertThat(stringSchema.isValid("string")).isTrue();
    }

    @Test
    public void testMinLengthIsValid() {
        stringSchema.minLength(4);
        assertThat(stringSchema.isValid("str")).isFalse();
        assertThat(stringSchema.isValid("string")).isTrue();
    }

    @Test
    public void testContainsIsValid() {
        stringSchema.contains("st");
        assertThat(stringSchema.isValid("int")).isFalse();
        assertThat(stringSchema.isValid("string")).isTrue();
    }

    @Test
    public void testFullValidationIsValid() {
        stringSchema.required().minLength(4).contains("st");
        assertThat(stringSchema.isValid("string")).isTrue();
        assertThat(stringSchema.isValid("st")).isFalse();
    }

    @Test
    public void testNoRequiredValidationIsValidIfNull() {
        stringSchema.minLength(4).contains("st");
        assertThat(stringSchema.isValid(null)).isFalse();
    }
}
