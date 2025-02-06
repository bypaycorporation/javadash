package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.some;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;

class SomeTest {

    @Test
    void testSomeWithMatchingElement() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Predicate<Integer> isEven = number -> number % 2 == 0;
        boolean result = some(numbers, isEven);
        assertTrue(result, "At least one element should satisfy the predicate (is even)");
    }

    @Test
    void testSomeWithNoMatchingElement() {
        List<Integer> numbers = Arrays.asList(1, 3, 5, 7, 9);
        Predicate<Integer> isEven = number -> number % 2 == 0;
        boolean result = some(numbers, isEven);
        assertFalse(result, "No element satisfies the predicate (is even)");
    }

    @Test
    void testSomeWithEmptyCollection() {
        List<Integer> emptyList = Collections.emptyList();
        Predicate<Integer> isEven = number -> number % 2 == 0;
        boolean result = some(emptyList, isEven);
        assertFalse(result, "Empty collection should return false");
    }

    @Test
    void testSomeWithNullCollection() {
        List<Integer> nullList = null;
        Predicate<Integer> isEven = number -> number % 2 == 0;
        boolean result = some(nullList, isEven);
        assertFalse(result, "Null collection should return false");
    }

    @Test
    void testSomeWithPredicateThatMatchesFirstElement() {
        List<String> strings = Arrays.asList("apple", "banana", "cherry");
        Predicate<String> startsWithA = s -> s.startsWith("a");
        boolean result = some(strings, startsWithA);
        assertTrue(result, "Predicate matches the first element");
    }

    @Test
    void testSomeWithPredicateThatMatchesNoElement() {
        List<String> strings = Arrays.asList("apple", "banana", "cherry");
        Predicate<String> startsWithZ = s -> s.startsWith("z");
        boolean result = some(strings, startsWithZ);
        assertFalse(result, "Predicate matches no element");
    }

    @Test
    void testSomeWithNullElementInCollection() {
        List<String> strings = Arrays.asList("apple", null, "cherry");
        Predicate<String> isNotNull = Objects::nonNull;
        boolean result = some(strings, isNotNull);
        assertTrue(result, "Predicate should match the non-null elements");
    }

    @Test
    void testSomeWithNullPredicate() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        assertThrows(NullPointerException.class, () -> some(numbers, null), "Predicate should not be null");
    }
}

