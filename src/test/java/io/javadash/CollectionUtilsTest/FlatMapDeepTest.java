package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.flatMapDeep;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import org.junit.jupiter.api.Test;
public class FlatMapDeepTest {
    @Test
    void testFlatMapDeep_validCollection() {
        List<String> collection = Arrays.asList("apple", "banana");
        Function<String, List<Object>> iteratee = s -> Arrays.asList(s.toUpperCase(), Arrays.asList(s.toLowerCase(), s.toUpperCase()));

        List<Object> result = flatMapDeep(collection, iteratee);
        assertEquals(6, result.size());
        assertTrue(result.contains("APPLE"));
        assertTrue(result.contains("banana"));
        assertTrue(result.contains("BANANA"));
        assertTrue(result.contains("apple"));
    }

    @Test
    void testFlatMapDeep_emptyCollection() {
        Collection<String> collection = Collections.emptyList();
        Function<String, Collection<Object>> iteratee = s -> Arrays.asList(s.toUpperCase(), s.toLowerCase());

        List<Object> result = flatMapDeep(collection, iteratee);

        assertTrue(result.isEmpty());  // Should return an empty list
    }

    @Test
    void testFlatMapDeep_nullCollection() {
        Collection<String> collection = null;
        Function<String, Collection<Object>> iteratee = s -> Arrays.asList(s.toUpperCase(), s.toLowerCase());

        List<Object> result = flatMapDeep(collection, iteratee);

        assertTrue(result.isEmpty());  // Null collection should return an empty list
    }

    @Test
    void testFlatMapDeep_nullIteratee() {
        Collection<String> collection = Arrays.asList("apple", "banana");

        assertThrows(NullPointerException.class, () -> flatMapDeep(collection, null));  // Null iteratee should throw exception
    }

    @Test
    void testFlatMapDeep_iterateeReturningNull() {
        Collection<String> collection = Arrays.asList("apple", "banana");
        Function<String, Collection<Object>> iteratee = s -> null;

        List<Object> result = flatMapDeep(collection, iteratee);

        assertTrue(result.isEmpty());  // Null returned by iteratee should be ignored
    }

    @Test
    void testFlatMapDeep_iterateeReturningEmptyCollection() {
        Collection<String> collection = Arrays.asList("apple", "banana");
        Function<String, Collection<Object>> iteratee = s -> Collections.emptyList();

        List<Object> result = flatMapDeep(collection, iteratee);

        assertTrue(result.isEmpty());  // Empty collection returned by iteratee should be ignored
    }

    @Test
    void testFlatMapDeep_recursiveFlattening() {
        Collection<Integer> collection = Arrays.asList(1, 2, 3);
        Function<Integer, Collection<Object>> iteratee = i -> Arrays.asList(i, Arrays.asList(i * 2,
            Collections.singletonList(i * 3)));

        List<Object> result = flatMapDeep(collection, iteratee);

        assertEquals(9, result.size());  // Each number produces a deep flattening
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertTrue(result.contains(2));
        assertTrue(result.contains(4));
        assertTrue(result.contains(6));
        assertTrue(result.contains(3));
        assertTrue(result.contains(6));
        assertTrue(result.contains(9));
    }

    @Test
    void testFlatMapDeep_complexNestedCollection() {
        Collection<String> collection = Arrays.asList("a", "b");
        Function<String, Collection<Object>> iteratee = s -> Arrays.asList(s, Arrays.asList(s + "1",
            Collections.singletonList(s + "2")));

        List<Object> result = flatMapDeep(collection, iteratee);

        assertEquals(6, result.size());  // Complex nested flattening
        assertTrue(result.contains("a"));
        assertTrue(result.contains("b"));
        assertTrue(result.contains("a1"));
        assertTrue(result.contains("b1"));
        assertTrue(result.contains("a2"));
        assertTrue(result.contains("b2"));
    }
}
