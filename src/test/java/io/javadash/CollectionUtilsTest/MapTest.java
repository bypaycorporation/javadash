package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.map;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class MapTest {

    @Test
    void testMap_validCollection() {
        List<String> collection = Arrays.asList("apple", "banana", "cherry");

        List<Integer> result = map(collection, String::length);  // Map strings to their lengths

        List<Integer> expected = Arrays.asList(5, 6, 6);

        assertEquals(expected, result);
    }

    @Test
    void testMap_collectionWithNullElements() {
        List<String> collection = Arrays.asList("apple", null, "banana", "cherry");

        List<Integer> result = map(collection, String::length);  // Map strings to their lengths

        List<Integer> expected = Arrays.asList(5, 6, 6);  // Null element should be skipped

        assertEquals(expected, result);
    }

    @Test
    void testMap_emptyCollection() {
        List<String> collection = Collections.emptyList();

        List<Integer> result = map(collection, String::length);  // Map strings to their lengths

        assertEquals(Collections.emptyList(), result);  // Should return an empty list
    }

    @Test
    void testMap_nullCollection() {
        List<String> collection = null;

        List<Integer> result = map(collection, String::length);  // Map strings to their lengths

        assertEquals(Collections.emptyList(), result);  // Should return an empty list
    }

    @Test
    void testMap_customMappingFunction() {
        List<String> collection = Arrays.asList("apple", "banana", "cherry");

        List<String> result = map(collection, String::toUpperCase);  // Map strings to uppercase

        List<String> expected = Arrays.asList("APPLE", "BANANA", "CHERRY");

        assertEquals(expected, result);
    }

    @Test
    void testMap_mixedDataTypes() {
        List<Integer> collection = Arrays.asList(1, 2, 3);

        List<String> result = map(collection, i -> "Number " + i);  // Map integers to a string format

        List<String> expected = Arrays.asList("Number 1", "Number 2", "Number 3");

        assertEquals(expected, result);
    }
}

