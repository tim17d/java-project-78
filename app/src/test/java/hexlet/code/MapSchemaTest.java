package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public final class MapSchemaTest {
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
    public void testIsValid() {
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
        mapSchema.required().sizeof(2);
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
        var schemas = new HashMap<String, BaseSchema<String>>();
        var v = new Validator();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(3));

        mapSchema.required().sizeof(2).shape(schemas);

        var human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertThat(mapSchema.isValid(human1)).isTrue();

        var human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", "S");
        assertThat(mapSchema.isValid(human2)).isFalse();

        var human3 = new HashMap<>();
        human3.put("firstName", "John");
        human3.put("lastName", null);
        assertThat(mapSchema.isValid(human3)).isFalse();

        var human4 = new HashMap<>();
        human4.put("firstName", null);
        human4.put("lastName", null);
        assertThat(mapSchema.isValid(human4)).isFalse();
    }

    @Test
    public void testFullValidationNotRequiredIsValid() {
        var schemas = new HashMap<String, BaseSchema<String>>();
        var v = new Validator();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(3));

        mapSchema.sizeof(2).shape(schemas);
        assertThat(mapSchema.isValid(null)).isTrue();
    }
}
