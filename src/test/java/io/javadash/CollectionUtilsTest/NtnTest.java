package io.javadash.CollectionUtilsTest;
import io.javadash.CollectionUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
public class NtnTest {

    @Test
    void testNth_nullCollection() {
        Optional<Integer> result = CollectionUtils.nth(null, 2);
        assertFalse(result.isPresent(), "Expected empty Optional when input collection is null");
    }

    @Test
    void testNth_emptyCollection() {
        Optional<String> result = CollectionUtils.nth(Collections.emptyList(), 0);
        assertFalse(result.isPresent(), "Expected empty Optional when input collection is empty");
    }

    @Test
    void testNth_validIndex() {
        List<String> input = Arrays.asList("apple", "banana", "cherry");
        Optional<String> result = CollectionUtils.nth(input, 1);

        assertTrue(result.isPresent(), "Expected Optional to contain a value");
        assertEquals("banana", result.get(), "Expected element at index 1 to be 'banana'");
    }

    @Test
    void testNth_negativeIndex_valid() {
        List<Integer> input = Arrays.asList(10, 20, 30, 40);
        Optional<Integer> result = CollectionUtils.nth(input, -2);

        assertTrue(result.isPresent(), "Expected Optional to contain a value");
        assertEquals(30, result.get(), "Expected element at index -2 to be 30");
    }

    @Test
    void testNth_negativeIndex_outOfBounds() {
        List<Integer> input = Arrays.asList(1, 2, 3);
        Optional<Integer> result = CollectionUtils.nth(input, -4);

        assertFalse(result.isPresent(), "Expected empty Optional for out-of-bounds negative index");
    }

    @Test
    void testNth_positiveIndex_outOfBounds() {
        List<String> input = Arrays.asList("X", "Y", "Z");
        Optional<String> result = CollectionUtils.nth(input, 5);

        assertFalse(result.isPresent(), "Expected empty Optional for out-of-bounds positive index");
    }

    @Test
    void testNth_firstElement() {
        List<String> input = Arrays.asList("A", "B", "C");
        Optional<String> result = CollectionUtils.nth(input, 0);

        assertTrue(result.isPresent(), "Expected Optional to contain a value");
        assertEquals("A", result.get(), "Expected first element to be 'A'");
    }

    @Test
    void testNth_lastElement() {
        List<Double> input = Arrays.asList(1.1, 2.2, 3.3);
        Optional<Double> result = CollectionUtils.nth(input, -1);

        assertTrue(result.isPresent(), "Expected Optional to contain a value");
        assertEquals(3.3, result.get(), "Expected last element to be 3.3");
    }
}
