package io.javadash.NumberUtilsTest;
import io.javadash.NumberUtils;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
public class MinByTest {
    // Test for minBy() with a string length comparison
    @Test
    void testMinByWithStringLength() {
        Function<String, Integer> lengthIteratee = String::length;
        Optional<String> result = NumberUtils.minBy(lengthIteratee, "apple", "banana", "cherry");
        assertTrue(result.isPresent(), "Result should be present");
        assertEquals("apple", result.get(), "The minimum value should be 'apple' by length");
    }

    // Test for minBy() with natural order comparison for Integer values
    @Test
    void testMinByWithIntegerValues() {
        Optional<Integer> result = NumberUtils.minBy(Function.identity(), 1, 5, 3, 7);
        assertTrue(result.isPresent(), "Result should be present");
        assertEquals(1, result.get(), "The minimum value should be 1");
    }

    // Test for minBy() with an empty array
    @Test
    void testMinByWithEmptyArray() {
        Optional<String> result = NumberUtils.minBy(String::length);
        assertTrue(!result.isPresent(), "Result should be empty when the array is empty");
    }

    // Test for minBy() with null values mixed with valid elements
    @Test
    void testMinByWithMixedNullValues() {
        Function<String, Integer> lengthIteratee = String::length;
        Optional<String> result = NumberUtils.minBy(lengthIteratee, "apple", null, "banana", "cherry", null);
        assertTrue(result.isPresent(), "Result should be present");
        assertEquals("apple", result.get(), "The minimum value should be 'apple' by length, ignoring nulls");
    }

    // Test for minBy() with null array
    @Test
    void testMinByWithNullArray() {
        Optional<String> result = NumberUtils.minBy(String::length, (String[]) null);
        assertTrue(!result.isPresent(), "Result should be empty when the array is null");
    }

    // Test for minBy() with all null values in the array
    @Test
    void testMinByWithAllNullValues() {
        Optional<String> result = NumberUtils.minBy(String::length, null, null);
        assertTrue(!result.isPresent(), "Result should be empty when all values are null");
    }

    // Test for minBy() with a single valid value
    @Test
    void testMinByWithSingleValue() {
        Function<String, Integer> lengthIteratee = String::length;
        Optional<String> result = NumberUtils.minBy(lengthIteratee, "apple");
        assertTrue(result.isPresent(), "Result should be present when there is only one value");
        assertEquals("apple", result.get(), "The minimum value should be 'apple'");
    }

    // Test for minBy() with mixed types and custom comparator
    @Test
    void testMinByWithCustomComparator() {
        Function<Integer, Integer> identityIteratee = Function.identity();
        Optional<Integer> result = NumberUtils.minBy(identityIteratee, 5, 3, 8, 1);
        assertTrue(result.isPresent(), "Result should be present");
        assertEquals(1, result.get(), "The minimum value should be 1");
    }
}
