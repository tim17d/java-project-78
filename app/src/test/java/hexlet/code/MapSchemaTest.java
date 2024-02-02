package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class MapSchemaTest {
    private MapSchema mapSchema;

    @BeforeEach
    public void init() {
        mapSchema = new MapSchema();
    }

    @Test
    public void testEmptyValidationIsValid() {
        assertThat(mapSchema.isValid(null)).isTrue();
    }

    @Test
    public void testRequiredIsValid() {
        mapSchema.required();
        assertThat(mapSchema.isValid(null)).isFalse();
        assertThat(mapSchema.isValid(Map.of())).isTrue();
        assertThat(mapSchema.isValid(Map.of("key", "value"))).isTrue();
    }

    @Test
    public void testSizeOfIsValid() {
        mapSchema.sizeOf(2);
        assertThat(mapSchema.isValid(null)).isFalse();
        assertThat(mapSchema.isValid(Map.of())).isFalse();
        assertThat(mapSchema.isValid(Map.of("key", "value"))).isFalse();
        assertThat(mapSchema.isValid(Map.of(
                "key1", "value1",
                "key2", "value2"
        ))).isTrue();
        assertThat(mapSchema.isValid(Map.of(
                "key1", "value1",
                "key2", "value2",
                "key3", "value3"
        ))).isFalse();
    }

    @Test
    public void testFullValidationIsValid() {
        mapSchema.required().sizeOf(2);
        assertThat(mapSchema.isValid(Map.of(
                "key1", "value1",
                "key2", "value2"
        ))).isTrue();
    }
}
