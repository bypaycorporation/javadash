package io.javadash.NumberUtilsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.javadash.NumberUtils;
import java.util.OptionalDouble;
import java.util.function.Function;
import org.junit.jupiter.api.Test;

public class MeanByTest {
    // Test for calculating the mean of string lengths using a custom iteratee
    @Test
    void testMeanByWithStringLengths() {
        Function<String, Integer> lengthIteratee = String::length;
        OptionalDouble result = NumberUtils.meanBy(lengthIteratee, "apple", "banana", "cherry");

        assertTrue(result.isPresent(), "Result should be present");
        assertEquals(5.666666666666667, result.getAsDouble(), "The mean length should be 5.666666666666667");
    }

    // Test for calculating the mean of custom numeric values (e.g., MyObject)
    @Test
    void testMeanByWithCustomObjects() {
        MyObject obj1 = new MyObject(10.0);
        MyObject obj2 = new MyObject(20.0);
        MyObject obj3 = new MyObject(30.0);

        Function<MyObject, Double> valueIteratee = MyObject::getValue;
        OptionalDouble result = NumberUtils.meanBy(valueIteratee, obj1, obj2, obj3);

        assertTrue(result.isPresent(), "Result should be present");
        assertEquals(20.0, result.getAsDouble(), "The mean value should be 20.0");
    }

    // Test for calculating the mean with an empty array
    @Test
    void testMeanByWithEmptyArray() {
        Function<String, Integer> lengthIteratee = String::length;
        OptionalDouble result = NumberUtils.meanBy(lengthIteratee);

        assertFalse(result.isPresent(), "Result should be empty when the array is empty");
    }

    // Test for calculating the mean with a null array
    @Test
    void testMeanByWithNullArray() {
        Function<String, Integer> lengthIteratee = String::length;
        OptionalDouble result = NumberUtils.meanBy(lengthIteratee, (String[]) null);

        assertFalse(result.isPresent(), "Result should be empty when the array is null");
    }

    // Test for calculating the mean with an array containing null values
    @Test
    void testMeanByWithNullValues() {
        Function<String, Integer> lengthIteratee = String::length;
        String[] values = {"apple", null, "cherry"};
        OptionalDouble result = NumberUtils.meanBy(lengthIteratee, values);

        assertTrue(result.isPresent(), "Result should be present even if some values are null");
        assertEquals(5.5, result.getAsDouble(), "The mean length should be 5.5");
    }

    // Test for calculating the mean with a single element in the array
    @Test
    void testMeanByWithSingleValue() {
        Function<String, Integer> lengthIteratee = String::length;
        String[] values = {"apple"};
        OptionalDouble result = NumberUtils.meanBy(lengthIteratee, values);

        assertTrue(result.isPresent(), "Result should be present with a single value");
        assertEquals(5.0, result.getAsDouble(), "The mean length should be 5.0");
    }

    // Test for calculating the mean with mixed types (using Integer and Double values)
    @Test
    void testMeanByWithMixedTypes() {
        MyObject obj1 = new MyObject(5.0);
        MyObject obj2 = new MyObject(10.0);
        MyObject obj3 = new MyObject(15.0);

        Function<MyObject, Double> valueIteratee = MyObject::getValue;
        OptionalDouble result = NumberUtils.meanBy(valueIteratee, obj1, obj2, obj3);

        assertTrue(result.isPresent(), "Result should be present");
        assertEquals(10.0, result.getAsDouble(), "The mean value should be 10.0");
    }

    // Test for calculating the mean with an array of numeric values of mixed types (Integer, Double)
    @Test
    void testMeanByWithMixedNumericTypes() {
        Function<Number, Double> numberIteratee = Number::doubleValue;
        Number[] values = {5, 10.5, 15, 20.5};
        OptionalDouble result = NumberUtils.meanBy(numberIteratee, values);

        assertTrue(result.isPresent(), "Result should be present");
        assertEquals(12.75, result.getAsDouble(), "The mean value should be 12.75");
    }
}

// Custom class for testing
class MyObject {
    private final Double value;

    public MyObject(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}