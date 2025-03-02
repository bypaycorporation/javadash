package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.minBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Function;
import org.junit.jupiter.api.Test;

public class MinByTest {
    @Test
    void testMinByWithNonEmptyCollection() {
        // Test with a simple collection of integers and a criterion of comparing the values themselves
        Collection<Integer> numbers = Arrays.asList(5, 3, 8, 1, 2);
        Optional<Integer> result = minBy(numbers, Function.identity());  // Identity function returns the number itself
        assertTrue(result.isPresent(), "The result should be present.");
        assertEquals(1, result.get(), "The minimum value should be 1.");
    }

    @Test
    void testMinByWithEmptyCollection() {
        Collection<Integer> numbers = Collections.emptyList();
        Optional<Integer> result = minBy(numbers, Function.identity());
        assertFalse(result.isPresent(), "The result should be empty for an empty collection.");
    }

    @Test
    void testMinByWithSingleElementCollection() {
        Collection<Integer> numbers = Collections.singletonList(42);
        Optional<Integer> result = minBy(numbers, Function.identity());
        assertTrue(result.isPresent(), "The result should be present for a single-element collection.");
        assertEquals(42, result.get(),
            "The minimum value in a single-element collection should be the element itself.");
    }

    @Test
    void testMinByWithNullValues() {
        Collection<Integer> numbers = Arrays.asList(1, 2, null, 3, 4);
        Optional<Integer> result = minBy(numbers, Function.identity());
        assertTrue(result.isPresent(), "The result should be present when the collection contains null values.");
        assertEquals(1, result.get(), "The minimum value should ignore null values and be 1.");
    }

    @Test
    void testMinByWithAllNullValues() {
        Collection<Integer> numbers = Arrays.asList(null, null, null);
        Optional<Integer> result = minBy(numbers, Function.identity());
        assertFalse(result.isPresent(), "The result should be empty when all elements are null.");
    }

    @Test
    void testMinByWithCustomCriterion() {
        // Test with a custom criterion - compare based on the absolute value of numbers
        Collection<Integer> numbers = Arrays.asList(-5, -10, 3, 2, 7);
        Optional<Integer> result =
            minBy(numbers, n -> Math.abs(n));  // Using absolute value as the comparison criterion
        assertTrue(result.isPresent(), "The result should be present when using a custom criterion.");
        assertEquals(2, result.get(), "The minimum value by absolute value should be 2.");
    }

    @Test
    void testMinByWithNullCollection() {
        Collection<Integer> numbers = null;
        Optional<Integer> result = minBy(numbers, Function.identity());
        assertFalse(result.isPresent(), "The result should be empty for a null collection.");
    }

    @Test
    void testMinByWithCustomStringLengthCriterion() {
        // Test with a custom criterion comparing by string length
        Collection<String> words = Arrays.asList("apple", "banana", "kiwi", "pear");
        Optional<String> result = minBy(words, String::length);  // Use string length as the comparison criterion
        assertTrue(result.isPresent(), "The result should be present.");
        assertEquals("kiwi", result.get(), "The minimum value by string length should be 'kiwi'.");
    }
}
