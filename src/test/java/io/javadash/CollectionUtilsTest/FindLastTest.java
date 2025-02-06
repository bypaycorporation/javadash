package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.findLast;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;

public class FindLastTest {

    @Test
    void testFindLast_elementFound() {
        Collection<String> collection = Arrays.asList("apple", "banana", "cherry", "banana");
        Predicate<String> predicate = s -> s.equals("banana");

        Optional<String> result = findLast(collection, predicate);

        assertTrue(result.isPresent());
        assertEquals("banana", result.get());  // Should return the last "banana"
    }

    @Test
    void testFindLast_elementNotFound() {
        Collection<String> collection = Arrays.asList("apple", "banana", "cherry");
        Predicate<String> predicate = s -> s.equals("orange");

        Optional<String> result = findLast(collection, predicate);

        assertFalse(result.isPresent());  // No matching element found
    }

    @Test
    void testFindLast_emptyCollection() {
        Collection<String> collection = Collections.emptyList();
        Predicate<String> predicate = s -> s.equals("banana");

        Optional<String> result = findLast(collection, predicate);

        assertFalse(result.isPresent());  // Collection is empty
    }

    @Test
    void testFindLast_nullCollection() {
        Collection<String> collection = null;
        Predicate<String> predicate = s -> s.equals("banana");

        Optional<String> result = findLast(collection, predicate);

        assertFalse(result.isPresent());  // Collection is null
    }

    @Test
    void testFindLast_nullPredicate() {
        Collection<String> collection = Arrays.asList("apple", "banana", "cherry");

        assertThrows(NullPointerException.class, () -> findLast(collection, null));  // Predicate is null
    }

    @Test
    void testFindLast_multipleMatches() {
        Collection<String> collection = Arrays.asList("apple", "banana", "cherry", "banana");
        Predicate<String> predicate = s -> s.equals("banana");

        Optional<String> result = findLast(collection, predicate);

        assertTrue(result.isPresent());
        assertEquals("banana", result.get());  // Should return the last "banana"
    }

    @Test
    void testFindLast_withNullElementInCollection() {
        Collection<String> collection = Arrays.asList("apple", "banana", null, "cherry");
        Predicate<String> predicate = s -> s.equals("banana");

        Optional<String> result = findLast(collection, predicate);

        assertTrue(result.isPresent());
        assertEquals("banana", result.get());  // Should return "banana" even if there is a null element
    }
}

