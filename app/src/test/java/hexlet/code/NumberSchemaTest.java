package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NumberSchemaTest {
    private NumberSchema numberSchema;

    @BeforeEach
    public void init() {
        numberSchema = new NumberSchema();
    }

    @Test
    public void testEmptyValidationIsValid() {
        assertThat(numberSchema.isValid(null)).isTrue();
    }

    @Test
    public void testIsValid() {
        assertThat(numberSchema.isValid(null)).isTrue();
    }

    @Test
    public void testRequiredIsValid() {
        numberSchema.required();
        assertThat(numberSchema.isValid(null)).isFalse();
        assertThat(numberSchema.isValid(1526)).isTrue();
    }

    @Test
    public void testPositiveIsValid() {
        numberSchema.required().positive();
        assertThat(numberSchema.isValid(null)).isFalse();
        assertThat(numberSchema.isValid(1526)).isTrue();
        assertThat(numberSchema.isValid(0)).isFalse();
        assertThat(numberSchema.isValid(-1526)).isFalse();
    }

    @Test
    public void testRangeIsValid() {
        numberSchema.required().range(5, 10);
        assertThat(numberSchema.isValid(null)).isFalse();
        assertThat(numberSchema.isValid(7)).isTrue();
        assertThat(numberSchema.isValid(5)).isTrue();
        assertThat(numberSchema.isValid(10)).isTrue();
        assertThat(numberSchema.isValid(1)).isFalse();
    }

    @Test
    public void testFullValidationIsValid() {
        numberSchema.required().positive().range(5, 10);
        assertThat(numberSchema.isValid(7)).isTrue();
        assertThat(numberSchema.isValid(-7)).isFalse();
    }

    @Test
    public void testFullValidationNotRequiredIsValid() {
        numberSchema.positive().range(5, 10);
        assertThat(numberSchema.isValid(null)).isTrue();
    }
}
