package io.javadash.NumberUtilsTest;

import static io.javadash.NumberUtils.maxBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import org.junit.jupiter.api.Test;

public class MaxByTest {
    // Test for maxBy() method with null values in the array and a custom comparator
    @Test
    void testMaxByWithNullValues() {
        Optional<String> result = maxBy(String::length, "apple", "banana", null, "cherry");
        assertTrue(result.isPresent(), "Result should be present");
        assertEquals("banana", result.get(), "The longest string should be 'banana'");
    }

    // Test for maxBy() method with a non-empty array of strings and custom comparator based on length
    @Test
    void testMaxByWithStrings() {
        Optional<String> result = maxBy(String::length, "apple", "banana", "cherry");
        assertTrue(result.isPresent(), "Result should be present");
        assertEquals("banana", result.get(), "The longest string should be 'banana'");
    }

    // Test for maxBy() method with a non-empty array of integers and custom comparator based on value
    @Test
    void testMaxByWithIntegers() {
        Optional<Integer> result = maxBy(Integer::intValue, 1, 5, 3, 7);
        assertTrue(result.isPresent(), "Result should be present");
        assertEquals(7, result.get(), "The maximum value should be 7");
    }

    // Test for maxBy() method with an array of equal values
    @Test
    void testMaxByWithEqualValues() {
        Optional<Integer> result = maxBy(Integer::intValue, 4, 4, 4, 4);
        assertTrue(result.isPresent(), "Result should be present");
        assertEquals(4, result.get(), "The result should be 4 since all values are the same");
    }

    // Test for maxBy() method with a single value in the array
    @Test
    void testMaxByWithSingleValue() {
        Optional<Integer> result = maxBy(Integer::intValue, 42);
        assertTrue(result.isPresent(), "Result should be present when there is only one value");
        assertEquals(42, result.get(), "The maximum value should be 42");
    }

    // Test for maxBy() method with an empty array
    @Test
    void testMaxByWithEmptyArray() {
        Optional<Integer> result = maxBy(Integer::intValue);
        assertTrue(!result.isPresent(), "Result should be empty when the array is empty");
    }

    // Test for maxBy() method with a null array (edge case)
    @Test
    void testMaxByWithNullArray() {
        Optional<String> result = maxBy(String::length, (String[]) null);
        assertTrue(!result.isPresent(), "Result should be empty when the array is null");
    }

    // Test for maxBy() method with custom comparator for strings with mixed case
    @Test
    void testMaxByWithStringCaseSensitivity() {
        Optional<String> result = maxBy(String::toUpperCase, "apple", "Banana", "cherry");
        assertTrue(result.isPresent(), "Result should be present");
        assertEquals("cherry", result.get(),
            "The string with the lexicographically greatest upper case value should be 'Banana'");
    }

    // Test for maxBy() method with all null values
    @Test
    void testMaxByWithAllNullValues() {
        Optional<String> result = maxBy(String::length, null, null, null);
        assertTrue(!result.isPresent(), "Result should be empty when all values are null");
    }
}
