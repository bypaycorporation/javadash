package io.javadash.CollectionUtilsTest;
import io.javadash.CollectionUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
public class LastTest {
    @Test
    void testLast_nullCollection() {
        Optional<Integer> result = CollectionUtils.last(null);
        assertFalse(result.isPresent(), "Expected empty Optional when input collection is null");
    }

    @Test
    void testLast_emptyCollection() {
        Optional<String> result = CollectionUtils.last(Collections.emptyList());
        assertFalse(result.isPresent(), "Expected empty Optional when input collection is empty");
    }

    @Test
    void testLast_singleElement() {
        Optional<Integer> result = CollectionUtils.last(Collections.singletonList(42));
        assertTrue(result.isPresent(), "Expected Optional to contain a value");
        assertEquals(42, result.get(), "Expected last element to be 42");
    }

    @Test
    void testLast_multipleElements_list() {
        List<String> input = Arrays.asList("apple", "banana", "cherry");
        Optional<String> result = CollectionUtils.last(input);

        assertTrue(result.isPresent(), "Expected Optional to contain a value");
        assertEquals("cherry", result.get(), "Expected last element to be 'cherry'");
    }

    @Test
    void testLast_multipleElements_set() {
        Set<Integer> input = new LinkedHashSet<>(Arrays.asList(1, 2, 3, 4));
        Optional<Integer> result = CollectionUtils.last(input);

        assertTrue(result.isPresent(), "Expected Optional to contain a value");
        assertEquals(4, result.get(), "Expected last element to be 4");
    }

    @Test
    void testLast_multipleElements_orderCheck() {
        List<Integer> input = new ArrayList<>(Arrays.asList(10, 20, 30, 40));
        Optional<Integer> result = CollectionUtils.last(input);

        assertTrue(result.isPresent(), "Expected Optional to contain a value");
        assertEquals(40, result.get(), "Expected last element to be 40");
    }

    @Test
    void testLast_unmodifiableCollection() {
        List<String> input = Collections.unmodifiableList(Arrays.asList("X", "Y", "Z"));
        Optional<String> result = CollectionUtils.last(input);

        assertTrue(result.isPresent(), "Expected Optional to contain a value");
        assertEquals("Z", result.get(), "Expected last element to be 'Z'");
    }
}
