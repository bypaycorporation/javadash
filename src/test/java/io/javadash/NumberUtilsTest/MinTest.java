package io.javadash.NumberUtilsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.javadash.NumberUtils;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class MinTest {
    // Test for min() method with an array of Integer values
    @Test
    void testMinWithIntegerValues() {
        Integer[] values = {1, 5, 3, 7};
        Optional<Integer> result = NumberUtils.min(values);
        assertTrue(result.isPresent(), "Result should be present");
        assertEquals(1, result.get(), "The minimum value should be 1");
    }

    // Test for min() method with an array of Double values
    @Test
    void testMinWithDoubleValues() {
        Double[] values = {1.5, 5.5, 3.5, 7.5};
        Optional<Double> result = NumberUtils.min(values);
        assertTrue(result.isPresent(), "Result should be present");
        assertEquals(1.5, result.get(), "The minimum value should be 1.5");
    }

    // Test for min() method with all null values in the array
    @Test
    void testMinWithAllNullValues() {
        Integer[] values = {null, null, null};
        Optional<Integer> result = NumberUtils.min(values);
        assertTrue(!result.isPresent(), "Result should be empty when all values are null");
    }

    // Test for min() method with an empty array
    @Test
    void testMinWithEmptyArray() {
        Integer[] values = {};
        Optional<Integer> result = NumberUtils.min(values);
        assertTrue(!result.isPresent(), "Result should be empty when the array is empty");
    }

    // Test for min() method with a null array
    @Test
    void testMinWithNullArray() {
        Optional<Integer> result = NumberUtils.min((Integer[]) null);
        assertTrue(!result.isPresent(), "Result should be empty when the array is null");
    }

    // Test for min() method with an array of equal values
    @Test
    void testMinWithEqualValues() {
        Integer[] values = {4, 4, 4, 4};
        Optional<Integer> result = NumberUtils.min(values);
        assertTrue(result.isPresent(), "Result should be present");
        assertEquals(4, result.get(), "The result should be 4 since all values are the same");
    }

    // Test for min() method with a single value in the array
    @Test
    void testMinWithSingleValue() {
        Integer[] values = {42};
        Optional<Integer> result = NumberUtils.min(values);
        assertTrue(result.isPresent(), "Result should be present when there is only one value");
        assertEquals(42, result.get(), "The minimum value should be 42");
    }

    // Test for min() method with null values mixed with valid numbers
    @Test
    void testMinWithMixedNullValues() {
        Integer[] values = {5, null, 2, null, 8};
        Optional<Integer> result = NumberUtils.min(values);
        assertTrue(result.isPresent(), "Result should be present");
        assertEquals(2, result.get(), "The minimum value should be 2, ignoring nulls");
    }

    // Test for min() method with a single null value
    @Test
    void testMinWithSingleNullValue() {
        Integer[] values = {null};
        Optional<Integer> result = NumberUtils.min(values);
        assertTrue(!result.isPresent(), "Result should be empty when the array contains only null");
    }
}
