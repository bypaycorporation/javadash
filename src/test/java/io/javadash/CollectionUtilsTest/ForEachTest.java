package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.forEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class ForEachTest {
    @Test
    void testForEach_validCollection() {
        Collection<String> collection = Arrays.asList("apple", "banana", "cherry");
        StringBuilder result = new StringBuilder();

        forEach(collection, s -> result.append(s).append(","));

        assertEquals("apple,banana,cherry,", result.toString());  // Should iterate and append all elements
    }

    @Test
    void testForEach_emptyCollection() {
        Collection<String> collection = Collections.emptyList();
        StringBuilder result = new StringBuilder();

        forEach(collection, s -> result.append(s).append(","));

        assertEquals("", result.toString());  // Should return an empty result for an empty collection
    }

    @Test
    void testForEach_nullCollection() {
        Collection<String> collection = null;
        StringBuilder result = new StringBuilder();

        forEach(collection, s -> result.append(s).append(","));

        assertEquals("", result.toString());  // Should do nothing for a null collection
    }

    @Test
    void testForEach_collectionWithNullElement() {
        Collection<String> collection = Arrays.asList("apple", null, "banana");
        StringBuilder result = new StringBuilder();

        forEach(collection, s -> result.append(s).append(","));

        assertEquals("apple,banana,", result.toString());  // Should skip null elements
    }

    @Test
    void testForEach_validMap() {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("cherry", 3);
        StringBuilder result = new StringBuilder();

        forEach(map, (key, value) -> result.append(key).append(":").append(value).append(","));

        assertEquals("apple:1,banana:2,cherry:3,", result.toString());  // Should iterate and append all key-value pairs
    }

    @Test
    void testForEach_emptyMap() {
        Map<String, Integer> map = Collections.emptyMap();
        StringBuilder result = new StringBuilder();

        forEach(map, (key, value) -> result.append(key).append(":").append(value).append(","));

        assertEquals("", result.toString());  // Should return an empty result for an empty map
    }

    @Test
    void testForEach_nullMap() {
        Map<String, Integer> map = null;
        StringBuilder result = new StringBuilder();

        forEach(map, (key, value) -> result.append(key).append(":").append(value).append(","));

        assertEquals("", result.toString());  // Should do nothing for a null map
    }

    @Test
    void testForEach_mapWithNullKeyOrValue() {
        Map<String, Integer> map = new HashMap<>();
        map.put("apple", 1);
        map.put("banana", null);
        map.put(null, 3);
        StringBuilder result = new StringBuilder();

        forEach(map, (key, value) -> result.append(key).append(":").append(value).append(","));

        assertEquals("apple:1,", result.toString());  // Should skip null key and null value
    }
}
