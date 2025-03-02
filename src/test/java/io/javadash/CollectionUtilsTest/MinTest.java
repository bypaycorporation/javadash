package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.min;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class MinTest {
    @Test
    void testMinWithNonEmptyCollection() {
        Collection<Integer> numbers = Arrays.asList(5, 3, 8, 1, 2);
        Optional<Integer> result = min(numbers);
        assertTrue(result.isPresent(), "The result should be present.");
        assertEquals(1, result.get(), "The minimum value should be 1.");
    }

    @Test
    void testMinWithEmptyCollection() {
        Collection<Integer> numbers = Collections.emptyList();
        Optional<Integer> result = min(numbers);
        assertFalse(result.isPresent(), "The result should be empty for an empty collection.");
    }

    @Test
    void testMinWithSingleElementCollection() {
        Collection<Integer> numbers = Collections.singletonList(42);
        Optional<Integer> result = min(numbers);
        assertTrue(result.isPresent(), "The result should be present for a single-element collection.");
        assertEquals(42, result.get(),
            "The minimum value in a single-element collection should be the element itself.");
    }

    @Test
    void testMinWithNullValues() {
        Collection<Integer> numbers = Arrays.asList(1, 2, null, 3, 4);
        Optional<Integer> result = min(numbers);
        assertTrue(result.isPresent(), "The result should be present when the collection contains null values.");
        assertEquals(1, result.get(), "The minimum value should ignore null values and be 1.");
    }

    @Test
    void testMinWithAllNullValues() {
        Collection<Integer> numbers = Arrays.asList(null, null, null);
        Optional<Integer> result = min(numbers);
        assertFalse(result.isPresent(), "The result should be empty when all elements are null.");
    }

    @Test
    void testMinWithEqualValues() {
        Collection<Integer> numbers = Arrays.asList(5, 5, 5, 5, 5);
        Optional<Integer> result = min(numbers);
        assertTrue(result.isPresent(), "The result should be present when all elements are the same.");
        assertEquals(5, result.get(), "The minimum value when all elements are the same should be the value itself.");
    }

    @Test
    void testMinWithNullCollection() {
        Collection<Integer> numbers = null;
        Optional<Integer> result = min(numbers);
        assertFalse(result.isPresent(), "The result should be empty for a null collection.");
    }
}
