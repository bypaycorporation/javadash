package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.flatMap;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import org.junit.jupiter.api.Test;
public class FlatMapTest {
    @Test
    void testFlatMap_validCollection() {
        Collection<String> collection = Arrays.asList("apple", "banana", "cherry");
        Function<String, Collection<String>> iteratee = s -> Arrays.asList(s.toUpperCase(), s.toLowerCase());

        List<String> result = flatMap(collection, iteratee);

        assertEquals(6, result.size());  // Each fruit maps to two values (upper and lower case)
        assertTrue(result.contains("APPLE"));
        assertTrue(result.contains("apple"));
        assertTrue(result.contains("BANANA"));
        assertTrue(result.contains("banana"));
        assertTrue(result.contains("CHERRY"));
        assertTrue(result.contains("cherry"));
    }

    @Test
    void testFlatMap_emptyCollection() {
        Collection<String> collection = Collections.emptyList();
        Function<String, Collection<String>> iteratee = s -> Arrays.asList(s.toUpperCase(), s.toLowerCase());

        List<String> result = flatMap(collection, iteratee);

        assertTrue(result.isEmpty());  // The result should be an empty list
    }

    @Test
    void testFlatMap_nullCollection() {
        Collection<String> collection = null;
        Function<String, Collection<String>> iteratee = s -> Arrays.asList(s.toUpperCase(), s.toLowerCase());

        List<String> result = flatMap(collection, iteratee);

        assertTrue(result.isEmpty());  // Null collection should result in an empty list
    }

    @Test
    void testFlatMap_nullIteratee() {
        Collection<String> collection = Arrays.asList("apple", "banana", "cherry");

        assertThrows(NullPointerException.class, () -> flatMap(collection, null));  // Null iteratee should throw exception
    }

    @Test
    void testFlatMap_iterateeReturningNull() {
        Collection<String> collection = Arrays.asList("apple", "banana", "cherry");
        Function<String, Collection<String>> iteratee = s -> null;

        List<String> result = flatMap(collection, iteratee);

        assertTrue(result.isEmpty());  // Null returned by iteratee should be ignored
    }

    @Test
    void testFlatMap_iterateeReturningEmptyCollection() {
        Collection<String> collection = Arrays.asList("apple", "banana", "cherry");
        Function<String, Collection<String>> iteratee = s -> Collections.emptyList();

        List<String> result = flatMap(collection, iteratee);

        assertTrue(result.isEmpty());  // Empty collection returned by iteratee should result in an empty list
    }

    @Test
    void testFlatMap_multipleElements() {
        Collection<Integer> collection = Arrays.asList(1, 2, 3);
        Function<Integer, Collection<Integer>> iteratee = i -> Arrays.asList(i, i * 2);

        List<Integer> result = flatMap(collection, iteratee);

        assertEquals(6, result.size());  // Each number produces two elements: the number and its double
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(2));
        assertTrue(result.contains(4));
        assertTrue(result.contains(3));
        assertTrue(result.contains(6));
    }
}
