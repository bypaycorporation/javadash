package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.includes;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class IncludesTest {
    @Test
    void testIncludes_validCollection_valueExists() {
        List<String> collection = Arrays.asList("apple", "banana", "cherry");

        assertTrue(includes(collection, "banana"));  // 'banana' should be found in the collection
    }

    @Test
    void testIncludes_validCollection_valueDoesNotExist() {
        List<String> collection = Arrays.asList("apple", "banana", "cherry");

        assertFalse(includes(collection, "grape"));  // 'grape' is not in the collection
    }

    @Test
    void testIncludes_emptyCollection() {
        List<String> collection = Collections.emptyList();

        assertFalse(includes(collection, "apple"));  // Empty collection should return false
    }

    @Test
    void testIncludes_nullCollection() {
        List<String> collection = null;

        assertFalse(includes(collection, "apple"));  // Null collection should return false
    }

    @Test
    void testIncludes_collectionWithNullElements() {
        List<String> collection = Arrays.asList("apple", null, "banana", "cherry");

        assertTrue(includes(collection, null));  // Null value should return true because 'null' is in the collection
        assertTrue(includes(collection, "banana"));  // 'banana' should be found in the collection
    }

    @Test
    void testIncludes_valueIsNull() {
        List<String> collection = Arrays.asList("apple", "banana", "cherry");

        assertFalse(includes(collection, null));  // 'null' is not in the collection
    }

    @Test
    void testIncludes_collectionWithMixedTypes() {
        List<Object> collection = Arrays.asList("apple", 5, 3.14, "banana");

        assertTrue(includes(collection, 5));  // '5' should be found in the collection
        assertFalse(includes(collection, "orange"));  // 'orange' is not in the collection
    }
}
