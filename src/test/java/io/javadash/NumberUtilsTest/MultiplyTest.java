package io.javadash.NumberUtilsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.javadash.NumberUtils;
import org.junit.jupiter.api.Test;

public class MultiplyTest {
    @Test
    void testMultiplyBothNonNull() {
        int result = NumberUtils.multiply(5, 4);
        assertEquals(20, result, "The product of 5 and 4 should be 20.");
    }

    @Test
    void testMultiplyFirstNull() {
        int result = NumberUtils.multiply(null, 4);
        assertEquals(1, result, "The result should be 1 if the first number is null.");
    }

    @Test
    void testMultiplySecondNull() {
        int result = NumberUtils.multiply(5, null);
        assertEquals(1, result, "The result should be 1 if the second number is null.");
    }

    @Test
    void testMultiplyBothNull() {
        Integer a = null;
        Integer b = null;
        int result = NumberUtils.multiply(a, b);
        assertEquals(1, result, "The result should be 1 if both numbers are null.");
    }

    @Test
    void testMultiplyZero() {
        int result = NumberUtils.multiply(0, 5);
        assertEquals(0, result, "The product of 0 and any number should be 0.");
    }

    @Test
    void testMultiplyBothNonNullDouble() {
        double result = NumberUtils.multiply(5.0, 4.0);
        assertEquals(20.0, result, "The product of 5.0 and 4.0 should be 20.0.");
    }

    @Test
    void testMultiplyFirstNullDouble() {
        Double a = null;
        double result = NumberUtils.multiply(a, 4.0);
        assertEquals(1.0, result, "The result should be 1.0 if the first number is null.");
    }

    @Test
    void testMultiplySecondNullDouble() {
        Double a = null;
        double result = NumberUtils.multiply(5.0, a);
        assertEquals(1.0, result, "The result should be 1.0 if the second number is null.");
    }

    @Test
    void testMultiplyBothNullDouble() {
        Double a = null;
        Double b = null;
        double result = NumberUtils.multiply(a, b);
        System.out.println(result);
        assertEquals(1.0, result, "The result should be 1.0 if both numbers are null.");
    }

    @Test
    void testMultiplyZeroDouble() {
        double result = NumberUtils.multiply(0.0, 5.0);
        assertEquals(0.0, result, "The product of 0.0 and any number should be 0.0.");
    }

    @Test
    void testMultiplyWithNegativeNumbers() {
        double result = NumberUtils.multiply(-5.0, 4.0);
        assertEquals(-20.0, result, "The product of -5.0 and 4.0 should be -20.0.");
    }

    @Test
    void testMultiplyWithDefaultValue() {
        double result = NumberUtils.multiply(null, 3.0);
        assertEquals(1.0, result, "The result should be 1.0 when one value is null.");
    }

    // FLOAT TEST CASE

    @Test
    void testMultiplyBothNonNullFloat() {
        // Test when both parameters are non-null
        float result = NumberUtils.multiply(5F, 4F);
        assertEquals(20F, result, "The product of 5.0F and 4.0F should be 20.0F.");
    }

    @Test
    void testMultiplyFirstNullFloat() {
        // Test when the first parameter is null
        float result = NumberUtils.multiply(null, 4F);
        assertEquals(1F, result, "The result should be 1.0F when the first number is null.");
    }

    @Test
    void testMultiplySecondNullFloat() {
        // Test when the second parameter is null
        float result = NumberUtils.multiply(5F, null);
        assertEquals(1F, result, "The result should be 1.0F when the second number is null.");
    }

    @Test
    void testMultiplyBothNullFloat() {
        // Test when both parameters are null
        Float a = null;
        Float b = null;
        float result = NumberUtils.multiply(a, b);
        assertEquals(1F, result, "The result should be 1.0F when both numbers are null.");
    }

    @Test
    void testMultiplyZeroFloat() {
        // Test when one of the numbers is 0.0F
        float result = NumberUtils.multiply(0F, 5F);
        assertEquals(0F, result, "The product of 0.0F and any number should be 0.0F.");
    }

    @Test
    void testMultiplyWithNegativeNumbersFloat() {
        // Test when one of the numbers is negative
        float result = NumberUtils.multiply(-5F, 4F);
        assertEquals(-20F, result, "The product of -5.0F and 4.0F should be -20.0F.");
    }

    @Test
    void testMultiplyWithDefaultValueWhenNullFloat() {
        // Test when the multiplier is null, expecting default value of 1.0F
        float result = NumberUtils.multiply(null, 3F);
        assertEquals(1F, result, "The result should be 1.0F when the multiplier is null.");
    }

    @Test
    void testMultiplyWithZeroAndNullFloat() {
        // Test when multiplier is null and multiplicand is 0.0F
        float result = NumberUtils.multiply(null, 0F);
        assertEquals(1F, result, "The result should be 1.0F when the multiplier is null and multiplicand is 0.0F.");
    }
}
