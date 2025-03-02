package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.max;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class MaxTest {
    @Test
    void testMaxWithNonEmptyCollection() {
        Collection<Integer> numbers = Arrays.asList(1, 5, 3, 8, 2);
        Optional<Integer> result = max(numbers);
        assertTrue(result.isPresent(), "The result should be present.");
        assertEquals(8, result.get(), "The maximum value should be 8.");
    }

    @Test
    void testMaxWithEmptyCollection() {
        Collection<Integer> numbers = Collections.emptyList();
        Optional<Integer> result = max(numbers);
        assertFalse(result.isPresent(), "The result should be empty for an empty collection.");
    }

    @Test
    void testMaxWithSingleElementCollection() {
        Collection<Integer> numbers = Collections.singletonList(42);
        Optional<Integer> result = max(numbers);
        assertTrue(result.isPresent(), "The result should be present for a single-element collection.");
        assertEquals(42, result.get(), "The maximum value should be 42.");
    }

    @Test
    void testMaxWithNullElements() {
        Collection<Integer> numbers = Arrays.asList(3, null, 2);
        Optional<Integer> result = max(numbers);
        assertEquals(3, result.get());
    }

    @Test
    void testMaxWithNullCollection() {
        Collection<Integer> numbers = null;
        Optional<Integer> result = max(numbers);
        assertFalse(result.isPresent(), "The result should be empty for a null collection.");
    }
}
