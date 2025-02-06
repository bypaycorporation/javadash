package io.javadash.CollectionUtilsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.javadash.CollectionUtils;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;

public class DifferenceWithTest {
    @Test
    void testDifferenceWith_nullCollection() {
        List<Integer> result =
            CollectionUtils.differenceWith(null, Objects::equals, Arrays.asList(1, 2));
        assertTrue(result.isEmpty(), "Expected empty list when input collection is null");
    }

    @Test
    void testDifferenceWith_emptyCollection() {
        List<Integer> result = CollectionUtils.differenceWith(Collections.emptyList(), Objects::equals,
            Arrays.asList(1, 2));
        assertTrue(result.isEmpty(), "Expected empty list when input collection is empty");
    }

    @Test
    void testDifferenceWith_nullValues() {
        List<Integer> input = Arrays.asList(1, 2, 3);
        List<Integer> result =
            CollectionUtils.differenceWith(input, Objects::equals, (Collection<Integer>[]) null);
        assertEquals(Arrays.asList(1, 2, 3), result, "Expected original list when exclude values are null");
    }

    @Test
    void testDifferenceWith_noExclusions() {
        List<Integer> input = Arrays.asList(1, 2, 3);
        List<Integer> result = CollectionUtils.differenceWith(input, Objects::equals);
        assertEquals(input, result, "Expected original list when no values to exclude are provided");
    }

    @Test
    void testDifferenceWith_simpleCase() {
        List<String> input = Arrays.asList("apple", "banana", "cherry");
        List<String> exclude = Arrays.asList("banana");

        // Using BiPredicate for case-sensitive comparison
        List<String> result = CollectionUtils.differenceWith(input, String::equals, exclude);

        assertEquals(Arrays.asList("apple", "cherry"), result, "Expected [apple, cherry] after excluding 'banana'");
    }

    @Test
    void testDifferenceWith_caseInsensitiveComparison() {
        List<String> input = Arrays.asList("Apple", "Banana", "Cherry");
        List<String> exclude = Arrays.asList("banana", "cherry");

        // BiPredicate for case-insensitive comparison
        List<String> result = CollectionUtils.differenceWith(input, String::equalsIgnoreCase, exclude);

        assertEquals(Collections.singletonList("Apple"), result, "Expected [Apple] after case-insensitive filtering");
    }

    @Test
    void testDifferenceWith_multipleExclusions() {
        List<Integer> input = Arrays.asList(1, 2, 3, 4);
        List<Integer> exclude1 = Arrays.asList(2, 3);
        List<Integer> exclude2 = Arrays.asList(4);

        // BiPredicate for integer equality
        List<Integer> result = CollectionUtils.differenceWith(input, Integer::equals, exclude1, exclude2);

        assertEquals(Collections.singletonList(1), result, "Expected [1] after multiple exclusions");
    }

    @Test
    void testDifferenceWith_noMatchingExclusions() {
        List<Double> input = Arrays.asList(2.1, 1.2, 3.5);
        List<Double> exclude = Arrays.asList(4.5, 5.6);

        // BiPredicate for floating-point equality
        List<Double> result = CollectionUtils.differenceWith(input, (a, b) -> a.equals(b), exclude);

        assertEquals(Arrays.asList(2.1, 1.2, 3.5), result, "Expected original list when no matches are found");
    }

    @Test
    void testDifferenceWith_customComparator() {
        List<Double> input = Arrays.asList(2.1, 1.2, 3.5);
        List<Double> exclude = Arrays.asList(2.3, 3.4);

        // BiPredicate using a custom comparison (floor-based)
        List<Double> result = CollectionUtils.differenceWith(input, (a, b) -> Math.floor(a) == Math.floor(b), exclude);

        assertEquals(Collections.singletonList(1.2), result, "Expected [1.2] after filtering based on Math.floor");
    }
}
