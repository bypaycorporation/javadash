package io.javadash.NumberUtilsTest;
import io.javadash.NumberUtils;
import org.junit.jupiter.api.Test;

import java.util.OptionalDouble;

import static org.junit.jupiter.api.Assertions.*;
public class MeanTest {
    // Test for calculating the mean of an array of integers
    @Test
    void testMeanWithIntegers() {
        Integer[] values = {1, 5, 3, 7};
        OptionalDouble result = NumberUtils.mean(values);
        assertTrue(result.isPresent(), "Result should be present");
        assertEquals(4.0, result.getAsDouble(), "The mean value should be 4.0");
    }

    // Test for calculating the mean of an array of doubles
    @Test
    void testMeanWithDoubles() {
        Double[] values = {1.5, 5.5, 3.5, 7.5};
        OptionalDouble result = NumberUtils.mean(values);
        assertTrue(result.isPresent(), "Result should be present");
        assertEquals(4.5, result.getAsDouble(), "The mean value should be 4.5");
    }

    // Test for calculating the mean with an empty array
    @Test
    void testMeanWithEmptyArray() {
        Integer[] values = {};
        OptionalDouble result = NumberUtils.mean(values);
        assertFalse(result.isPresent(), "Result should be empty when the array is empty");
    }

    // Test for calculating the mean with a null array
    @Test
    void testMeanWithNullArray() {
        OptionalDouble result = NumberUtils.mean((Integer[]) null);
        assertFalse(result.isPresent(), "Result should be empty when the array is null");
    }

    // Test for calculating the mean with an array of null values
    @Test
    void testMeanWithNullValues() {
        Integer[] values = {null, null, null};
        OptionalDouble result = NumberUtils.mean(values);
        assertFalse(result.isPresent(), "Result should be empty when the array contains only null values");
    }

    // Test for calculating the mean with a single value in the array
    @Test
    void testMeanWithSingleValue() {
        Integer[] values = {5};
        OptionalDouble result = NumberUtils.mean(values);
        assertTrue(result.isPresent(), "Result should be present when there is only one value");
        assertEquals(5.0, result.getAsDouble(), "The mean value should be 5.0");
    }

    // Test for calculating the mean with mixed types (Integer and Double)
    @Test
    void testMeanWithMixedTypes() {
        Number[] values = {1, 5.5, 3, 7};
        OptionalDouble result = NumberUtils.mean(values);
        assertTrue(result.isPresent(), "Result should be present");
        assertEquals(4.125, result.getAsDouble(), "The mean value should be 4.125");
    }

    // Test for calculating the mean with some null values in the array
    @Test
    void testMeanWithSomeNullValues() {
        Number[] values = {1, null, 3, 5};
        OptionalDouble result = NumberUtils.mean(values);
        assertTrue(result.isPresent(), "Result should be present");
        assertEquals(3.0, result.getAsDouble(), "The mean value should be 3.0, ignoring null values");
    }
}
