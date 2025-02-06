package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.forEachRight;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class ForEachRightTest {
    @Test
    void testForEachRight_validCollection() {
        List<String> collection = Arrays.asList("apple", "banana", "cherry");
        StringBuilder result = new StringBuilder();

        forEachRight(collection, s -> result.append(s).append(","));

        assertEquals("cherry,banana,apple,", result.toString());  // Should iterate in reverse order
    }

    @Test
    void testForEachRight_emptyCollection() {
        List<String> collection = Collections.emptyList();
        StringBuilder result = new StringBuilder();

        forEachRight(collection, s -> result.append(s).append(","));

        assertEquals("", result.toString());  // Should return an empty result for an empty collection
    }

    @Test
    void testForEachRight_nullCollection() {
        List<String> collection = null;
        StringBuilder result = new StringBuilder();

        forEachRight(collection, s -> result.append(s).append(","));

        assertEquals("", result.toString());  // Should do nothing for a null collection
    }

    @Test
    void testForEachRight_collectionWithNullElement() {
        List<String> collection = Arrays.asList("apple", null, "banana");
        StringBuilder result = new StringBuilder();

        forEachRight(collection, s -> result.append(s).append(","));

        assertEquals("banana,apple,", result.toString());  // Should skip null elements
    }

    @Test
    void testForEachRight_validMap() {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("cherry", 3);
        StringBuilder result = new StringBuilder();

        forEachRight(map, (key, value) -> result.append(key).append(":").append(value).append(","));

        assertEquals("cherry:3,banana:2,apple:1,", result.toString());  // Should iterate in reverse order
    }

    @Test
    void testForEachRight_emptyMap() {
        Map<String, Integer> map = Collections.emptyMap();
        StringBuilder result = new StringBuilder();

        forEachRight(map, (key, value) -> result.append(key).append(":").append(value).append(","));

        assertEquals("", result.toString());  // Should return an empty result for an empty map
    }

    @Test
    void testForEachRight_nullMap() {
        Map<String, Integer> map = null;
        StringBuilder result = new StringBuilder();

        forEachRight(map, (key, value) -> result.append(key).append(":").append(value).append(","));

        assertEquals("", result.toString());  // Should do nothing for a null map
    }

    @Test
    void testForEachRight_mapWithNullKeyOrValue() {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("apple", 1);
        map.put("banana", null);
        map.put(null, 3);
        StringBuilder result = new StringBuilder();

        forEachRight(map, (key, value) -> result.append(key).append(":").append(value).append(","));

        assertEquals("apple:1,", result.toString());  // Should skip null key and null value
    }
}
