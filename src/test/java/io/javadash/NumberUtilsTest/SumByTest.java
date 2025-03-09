package io.javadash.NumberUtilsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.javadash.NumberUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import org.junit.jupiter.api.Test;

public class SumByTest {
    @Test
    void testSumByInt() {
        // Test with valid collection and iteratee function
        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        Function<Integer, Integer> iteratee = (i) -> i * 2;  // Double each value
        assertEquals(20, NumberUtils.sumByInt(integers, iteratee), "Sum should be 20 (1*2 + 2*2 + 3*2 + 4*2)");

        // Test with null values in the collection
        List<Integer> integersWithNulls = Arrays.asList(1, null, 3, null);
        assertEquals(8, NumberUtils.sumByInt(integersWithNulls, iteratee), "Sum should be 8 (1*2 + 3*2)");

        // Test with an empty collection
        List<Integer> emptyList = Collections.emptyList();
        assertEquals(0, NumberUtils.sumByInt(emptyList, iteratee), "Sum of an empty collection should be 0");

        // Test with null collection
        assertEquals(0, NumberUtils.sumByInt(null, iteratee), "Sum of null collection should be 0");
    }

    // Test sumByLong
    @Test
    void testSumByLong() {
        // Test with valid collection and iteratee function
        List<Long> longs = Arrays.asList(10L, 20L, 30L);
        Function<Long, Long> iteratee = (l) -> l * 2;  // Double each value
        assertEquals(120L, NumberUtils.sumByLong(longs, iteratee), "Sum should be 120 (10*2 + 20*2 + 30*2)");

        // Test with null values in the collection
        List<Long> longsWithNulls = Arrays.asList(10L, null, 30L, null);
        assertEquals(80L, NumberUtils.sumByLong(longsWithNulls, iteratee), "Sum should be 80 (10*2 + 30*2)");

        // Test with an empty collection
        List<Long> emptyList = Collections.emptyList();
        assertEquals(0L, NumberUtils.sumByLong(emptyList, iteratee), "Sum of an empty collection should be 0");

        // Test with null collection
        assertEquals(0L, NumberUtils.sumByLong(null, iteratee), "Sum of null collection should be 0");
    }

    // Test sumByFloat
    @Test
    void testSumByFloat() {
        // Test with valid collection and iteratee function
        List<Float> floats = Arrays.asList(1.5F, 2.5F, 3.5F);
        Function<Float, Float> iteratee = (f) -> f * 2;  // Double each value
        assertEquals(15.0F, NumberUtils.sumByFloat(floats, iteratee), "Sum should be 15.0 (1.5*2 + 2.5*2 + 3.5*2)");

        // Test with null values in the collection
        List<Float> floatsWithNulls = Arrays.asList(1.5F, null, 3.5F, null);
        assertEquals(10.0F, NumberUtils.sumByFloat(floatsWithNulls, iteratee), "Sum should be 10.0 (1.5*2 + 3.5*2)");

        // Test with an empty collection
        List<Float> emptyList = Collections.emptyList();
        assertEquals(0.0F, NumberUtils.sumByFloat(emptyList, iteratee), "Sum of an empty collection should be 0.0F");

        // Test with null collection
        assertEquals(0.0F, NumberUtils.sumByFloat(null, iteratee), "Sum of null collection should be 0.0F");
    }

    // Test sumByDouble
    @Test
    void testSumByDouble() {
        // Test with valid collection and iteratee function
        List<Double> doubles = Arrays.asList(1.1, 2.2, 3.3);
        Function<Double, Double> iteratee = (d) -> d * 2;  // Double each value
        assertEquals(13.2, NumberUtils.sumByDouble(doubles, iteratee), "Sum should be 13.2 (1.1*2 + 2.2*2 + 3.3*2)");

        // Test with null values in the collection
        List<Double> doublesWithNulls = Arrays.asList(1.1, null, 3.3, null);
        assertEquals(8.8, NumberUtils.sumByDouble(doublesWithNulls, iteratee), "Sum should be 8.8 (1.1*2 + 3.3*2)");

        // Test with an empty collection
        List<Double> emptyList = Collections.emptyList();
        assertEquals(0.0, NumberUtils.sumByDouble(emptyList, iteratee), "Sum of an empty collection should be 0.0");

        // Test with null collection
        assertEquals(0.0, NumberUtils.sumByDouble(null, iteratee), "Sum of null collection should be 0.0");
    }
}
