package io.javadash.CollectionUtilsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.javadash.CollectionUtils;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DifferenceByTest {
    @Test
    void testDifferenceBy_nullCollection() {
        List<Integer> result = CollectionUtils.differenceBy(null, x -> x > 2, Arrays.asList(1, 2));
        assertTrue(result.isEmpty(), "Expected empty list when input collection is null");
    }

    @Test
    void testDifferenceBy_emptyCollection() {
        List<Integer> result = CollectionUtils.differenceBy(Collections.emptyList(), x -> x > 2, Arrays.asList(1, 2));
        assertTrue(result.isEmpty(), "Expected empty list when input collection is empty");
    }

    @Test
    void testDifferenceBy_nullValues() {
        List<Integer> result =
            CollectionUtils.differenceBy(Arrays.asList(1, 2, 3), x -> x > 2, (Collection<Integer>[]) null);
        assertEquals(Arrays.asList(1, 2, 3), result, "Expected original list when exclude values are null");
    }

    @Test
    void testDifferenceBy_noExclusions() {
        List<Integer> input = Arrays.asList(1, 2, 3);
        List<Integer> result = CollectionUtils.differenceBy(input, x -> x > 2);
        assertEquals(input, result, "Expected original list when no values to exclude are provided");
    }

    @Test
    void testDifferenceBy_simpleCase() {
        List<Double> input = Arrays.asList(2.1, 1.2, 3.5);
        List<Double> exclude = Arrays.asList(2.3, 3.4);

        List<Double> result = CollectionUtils.differenceBy(input, x -> x < 2, exclude);

        assertEquals(Collections.singletonList(1.2), result, "Expected [1.2] after filtering based on Math.floor");
    }

    @Test
    void testDifferenceBy_multipleExclusions() {
        List<Double> input = Arrays.asList(2.1, 1.2, 3.5, 4.8);
        List<Double> exclude1 = Arrays.asList(2.3, 3.4);
        List<Double> exclude2 = Arrays.asList(1.9, 4.1);

        List<Double> result = CollectionUtils.differenceBy(input, x -> x < 2, exclude1, exclude2);
        assertEquals(Arrays.asList(1.2), result);
    }

    @Test
    void testDifferenceBy_noMatchingExclusions() {
        List<Double> input = Arrays.asList(2.1, 1.2, 3.5);
        List<Double> exclude = Arrays.asList(4.5, 5.6);

        List<Double> result = CollectionUtils.differenceBy(input, x -> x > 2, exclude);

        assertEquals(Arrays.asList(2.1, 3.5), result, "Expected list when no matches are found");
    }
}
