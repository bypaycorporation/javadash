package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.keyBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class KeyByTest {

    @Test
    void testKeyBy_validCollection_uniqueKeys() {
        List<String> collection = Arrays.asList("apple", "banana", "cherry");

        Map<Integer, String> result = keyBy(collection, String::length);  // Group by string length

        Map<Integer, String> expected = new HashMap<>();
        expected.put(5, "apple");
        expected.put(6, "banana");
        expected.put(6, "cherry");  // The last element with the same key (6) will overwrite the previous

        assertEquals(expected, result);
    }

    @Test
    void testKeyBy_collectionWithDuplicateKeys() {
        List<String> collection = Arrays.asList("apple", "banana", "apricot");

        Map<Object, String> result = keyBy(collection, s -> s.charAt(0));  // Group by first character

        Map<Object, String> expected = new HashMap<>();
        expected.put('a', "apricot");  // "apricot" will overwrite "apple" for the key 'a'
        expected.put('b', "banana");

        assertEquals(expected, result);
    }

    @Test
    void testKeyBy_emptyCollection() {
        List<String> collection = Collections.emptyList();

        Map<Object, String> result = keyBy(collection, String::length);  // Group by string length

        assertEquals(Collections.emptyMap(), result);  // Should return an empty map
    }

    @Test
    void testKeyBy_nullCollection() {
        List<String> collection = null;

        Map<Object, String> result = keyBy(collection, String::length);  // Group by string length

        assertEquals(Collections.emptyMap(), result);  // Should return an empty map
    }

    @Test
    void testKeyBy_collectionWithNullElements() {
        List<String> collection = Arrays.asList("apple", null, "banana");

        Map<Object, String> result =
            keyBy(collection, s ->s.charAt(0));  // Group by first character

        Map<Object, String> expected = new HashMap<>();
        expected.put('a', "apple");
        expected.put('b', "banana");  // The null value will result in a null key

        assertEquals(expected, result);
    }

    @Test
    void testKeyBy_customKeyExtractionLogic() {
        List<String> collection = Arrays.asList("apple", "banana", "kiwi", "avocado");

        Map<Object, String> result = keyBy(collection, s -> s.substring(0, 1));  // Group by first letter

        Map<Object, String> expected = new HashMap<>();
        expected.put("a", "avocado");
        expected.put("b", "banana");
        expected.put("k", "kiwi");

        assertEquals(expected, result);
    }

    @Test
    void testKeyBy_keyGenerationIsNull() {
        List<String> collection = Arrays.asList("apple", "banana", "cherry");

        Map<Object, String> result = keyBy(collection, s -> null);  // Generate null keys for all elements

        Map<Object, String> expected = new HashMap<>();
        expected.put(null, "cherry");  // The last element will overwrite with null key

        assertEquals(expected, result);
    }
}

