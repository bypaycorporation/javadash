package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.flatMapDepth;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import org.junit.jupiter.api.Test;

public class FlatMapDepthTest {

    @Test
    void testFlatMapDepth_validCollection_depth1() {
        Collection<String> collection = Arrays.asList("apple", "banana");
        Function<String, Collection<Object>> iteratee = s -> Arrays.asList(s.toUpperCase(),
            Collections.singletonList(Arrays.asList(s.toLowerCase(), s.toUpperCase())));

        List<Object> result = flatMapDepth(collection, iteratee, 1);
        assertEquals(4, result.size());
        assertTrue(result.contains("APPLE"));
        assertTrue(result.contains("BANANA"));
        assertTrue(result.contains(Arrays.asList("apple", "APPLE")));
        assertTrue(result.contains(Arrays.asList("banana", "BANANA")));
    }

    @Test
    void testFlatMapDepth_validCollection_depth2() {
        Collection<String> collection = Arrays.asList("apple", "banana");
        Function<String, Collection<Object>> iteratee = s -> Arrays.asList(s.toUpperCase(), Arrays.asList(s.toLowerCase(), s.toUpperCase()));

        List<Object> result = flatMapDepth(collection, iteratee, 2);

        assertEquals(6, result.size());  // Second level flattening
        assertTrue(result.contains("APPLE"));
        assertTrue(result.contains("banana"));
        assertTrue(result.contains("BANANA"));
        assertTrue(result.contains("apple"));
    }

    @Test
    void testFlatMapDepth_emptyCollection() {
        Collection<String> collection = Collections.emptyList();
        Function<String, Collection<Object>> iteratee = s -> Arrays.asList(s.toUpperCase(), s.toLowerCase());

        List<Object> result = flatMapDepth(collection, iteratee, 2);

        assertTrue(result.isEmpty());  // Should return an empty list
    }

    @Test
    void testFlatMapDepth_nullCollection() {
        Collection<String> collection = null;
        Function<String, Collection<Object>> iteratee = s -> Arrays.asList(s.toUpperCase(), s.toLowerCase());

        List<Object> result = flatMapDepth(collection, iteratee, 2);

        assertTrue(result.isEmpty());  // Null collection should return an empty list
    }

    @Test
    void testFlatMapDepth_depthZero() {
        Collection<String> collection = Arrays.asList("apple", "banana");
        Function<String, Collection<Object>> iteratee = s -> Arrays.asList(s.toUpperCase(), Arrays.asList(s.toLowerCase(), s.toUpperCase()));

        List<Object> result = flatMapDepth(collection, iteratee, 0);

        assertEquals(6, result.size());  // Second level flattening
        assertTrue(result.contains("APPLE"));
        assertTrue(result.contains("banana"));
        assertTrue(result.contains("BANANA"));
        assertTrue(result.contains("apple"));
    }

    @Test
    void testFlatMapDepth_invalidDepth() {
        Collection<String> collection = Arrays.asList("apple", "banana");
        Function<String, Collection<Object>> iteratee = s -> Arrays.asList(s.toUpperCase(), Arrays.asList(s.toLowerCase(), s.toUpperCase()));

        List<Object> result = flatMapDepth(collection, iteratee, -1);
        assertEquals(6, result.size());  // Second level flattening
        assertTrue(result.contains("APPLE"));
        assertTrue(result.contains("banana"));
        assertTrue(result.contains("BANANA"));
        assertTrue(result.contains("apple"));
    }

    @Test
    void testFlatMapDepth_depthGreaterThanActualDepth() {
        Collection<Integer> collection = Arrays.asList(1, 2, 3);
        Function<Integer, Collection<Object>> iteratee = i -> Arrays.asList(i, Arrays.asList(i * 2,
            Collections.singletonList(i * 3)));

        List<Object> result = flatMapDepth(collection, iteratee, 3);

        assertEquals(9, result.size());  // The depth should flatten all levels, as depth is more than required
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
    void testFlatMapDepth_depthEqualsCollectionSize() {
        Collection<String> collection = Arrays.asList("a", "b");
        Function<String, Collection<Object>> iteratee = s -> Arrays.asList(s, Arrays.asList(s + "1",
            Collections.singletonList(s + "2")));

        List<Object> result = flatMapDepth(collection, iteratee, 2);

        assertEquals(6, result.size());  // Flattening up to depth 2
        assertTrue(result.contains("a"));
        assertTrue(result.contains("b"));
        assertTrue(result.contains("a1"));
        assertTrue(result.contains("b1"));
        assertTrue(result.contains("a2"));
        assertTrue(result.contains("b2"));
    }
}

