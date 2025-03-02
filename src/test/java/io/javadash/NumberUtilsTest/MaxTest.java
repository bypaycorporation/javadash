package io.javadash.NumberUtilsTest;

import static io.javadash.NumberUtils.max;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import org.junit.jupiter.api.Test;

public class MaxTest {
    // Test for max() method with null array
    @Test
    void testMaxWithNullArray() {
        Optional<Integer> result = max((Integer[]) null);
        assertFalse(result.isPresent(), "Result should be empty when input is null");
    }

    // Test for max() method with an empty array
    @Test
    void testMaxWithEmptyArray() {
        Optional<Integer> result = max();
        assertFalse(result.isPresent(), "Result should be empty when input array is empty");
    }

    // Test for max() method with null values in the array
    @Test
    void testMaxWithNullValues() {
        Optional<Integer> result = max(1, 5, null, 3, 7);
        assertTrue(result.isPresent(), "Result should be present");
        assertEquals(7, result.get(), "The maximum value should be 7");
    }

    // Test for max() method with an array of integers
    @Test
    void testMaxWithIntegers() {
        Optional<Integer> result = max(1, 5, 3, 7);
        assertTrue(result.isPresent(), "Result should be present");
        assertEquals(7, result.get(), "The maximum value should be 7");
    }

    // Test for max() method with an array of doubles
    @Test
    void testMaxWithDoubles() {
        Optional<Double> result = max(1.2, 3.5, 2.1, 4.6);
        assertTrue(result.isPresent(), "Result should be present");
        assertEquals(4.6, result.get(), "The maximum value should be 4.6");
    }

    // Test for max() method with all null values
    @Test
    void testMaxWithAllNullValues() {
        Optional<Integer> result = max(null, null, null);
        assertFalse(result.isPresent(), "Result should be empty when all values are null");
    }

    // Test for max() method with a single value
    @Test
    void testMaxWithSingleValue() {
        Optional<Integer> result = max(42);
        assertTrue(result.isPresent(), "Result should be present when there is only one value");
        assertEquals(42, result.get(), "The maximum value should be 42");
    }
}
