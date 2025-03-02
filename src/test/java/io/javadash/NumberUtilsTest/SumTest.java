package io.javadash.NumberUtilsTest;
import static org.junit.jupiter.api.Assertions.*;

import io.javadash.NumberUtils;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SumTest {
    // Test sumInt
    @Test
    void testSumInt() {
        // Test with valid collection
        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        assertEquals(10, NumberUtils.sumInt(integers), "Sum should be 10");

        // Test with null values in the collection
        List<Integer> integersWithNulls = Arrays.asList(1, null, 3, null);
        assertEquals(4, NumberUtils.sumInt(integersWithNulls), "Sum should be 4, null values are ignored");

        // Test with an empty collection
        List<Integer> emptyList = Arrays.asList();
        assertEquals(0, NumberUtils.sumInt(emptyList), "Sum of an empty collection should be 0");

        // Test with null collection
        assertEquals(0, NumberUtils.sumInt(null), "Sum of null collection should be 0");
    }

    // Test sumLong
    @Test
    void testSumLong() {
        // Test with valid collection
        List<Long> longs = Arrays.asList(10L, 20L, 30L);
        assertEquals(60L, NumberUtils.sumLong(longs), "Sum should be 60");

        // Test with null values in the collection
        List<Long> longsWithNulls = Arrays.asList(10L, null, 30L, null);
        assertEquals(40L, NumberUtils.sumLong(longsWithNulls), "Sum should be 40, null values are ignored");

        // Test with an empty collection
        List<Long> emptyList = Arrays.asList();
        assertEquals(0L, NumberUtils.sumLong(emptyList), "Sum of an empty collection should be 0");

        // Test with null collection
        assertEquals(0L, NumberUtils.sumLong(null), "Sum of null collection should be 0");
    }

    // Test sumFloat
    @Test
    void testSumFloat() {
        // Test with valid collection
        List<Float> floats = Arrays.asList(1.5F, 2.5F, 3.5F);
        assertEquals(7.5F, NumberUtils.sumFloat(floats), "Sum should be 7.5");

        // Test with null values in the collection
        List<Float> floatsWithNulls = Arrays.asList(1.5F, null, 3.5F, null);
        assertEquals(5.0F, NumberUtils.sumFloat(floatsWithNulls), "Sum should be 5.0, null values are ignored");

        // Test with an empty collection
        List<Float> emptyList = Arrays.asList();
        assertEquals(0.0F, NumberUtils.sumFloat(emptyList), "Sum of an empty collection should be 0.0F");

        // Test with null collection
        assertEquals(0.0F, NumberUtils.sumFloat(null), "Sum of null collection should be 0.0F");
    }

    // Test sumDouble
    @Test
    void testSumDouble() {
        // Test with valid collection
        List<Double> doubles = Arrays.asList(1.1, 2.2, 3.3);
        assertEquals(6.6, NumberUtils.sumDouble(doubles), "Sum should be 6.6");

        // Test with null values in the collection
        List<Double> doublesWithNulls = Arrays.asList(1.1, null, 3.3, null);
        assertEquals(4.4, NumberUtils.sumDouble(doublesWithNulls), "Sum should be 4.4, null values are ignored");

        // Test with an empty collection
        List<Double> emptyList = Arrays.asList();
        assertEquals(0.0, NumberUtils.sumDouble(emptyList), "Sum of an empty collection should be 0.0");

        // Test with null collection
        assertEquals(0.0, NumberUtils.sumDouble(null), "Sum of null collection should be 0.0");
    }
}
