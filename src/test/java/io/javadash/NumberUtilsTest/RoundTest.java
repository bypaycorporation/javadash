package io.javadash.NumberUtilsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.javadash.NumberUtils;
import org.junit.jupiter.api.Test;

public class RoundTest {

    @Test
    void testRoundPositiveNumber() {
        // Test when the number is a positive value.
        double result = NumberUtils.round(5.4);
        assertEquals(5.0, result, "The number 5.4 should be rounded to 5.0.");

        result = NumberUtils.round(5.5);
        assertEquals(6.0, result, "The number 5.5 should be rounded to 6.0.");

        result = NumberUtils.round(5.9);
        assertEquals(6.0, result, "The number 5.9 should be rounded to 6.0.");
    }

    @Test
    void testRoundNegativeNumber() {
        // Test when the number is a negative value.
        double result = NumberUtils.round(-5.4);
        assertEquals(-5.0, result, "The number -5.4 should be rounded to -5.0.");

        result = NumberUtils.round(-5.5);
        assertEquals(-5.0, result, "The number -5.5 should be rounded to -5.0.");

        result = NumberUtils.round(-5.9);
        assertEquals(-6.0, result, "The number -5.9 should be rounded to -6.0.");
    }

    @Test
    void testRoundZero() {
        // Test when the number is zero.
        double result = NumberUtils.round(0.0);
        assertEquals(0.0, result, "The number 0.0 should be rounded to 0.0.");

        result = NumberUtils.round(-0.0);
        assertEquals(0.0, result, "The number -0.0 should be rounded to 0.0.");
    }

    @Test
    void testRoundEdgeCases() {
        // Test some edge cases
        double result = NumberUtils.round(0.1);
        assertEquals(0.0, result, "The number 0.1 should be rounded to 0.0.");

        result = NumberUtils.round(0.9);
        assertEquals(1.0, result, "The number 0.9 should be rounded to 1.0.");

        result = NumberUtils.round(1.5);
        assertEquals(2.0, result, "The number 1.5 should be rounded to 2.0.");

        result = NumberUtils.round(-0.5);
        assertEquals(0.0, result, "The number -0.5 should be rounded to 0.0.");
    }

    @Test
    void testRoundWithZeroPrecision() {
        // Test rounding with precision 0, effectively rounding to the nearest integer.
        double result = NumberUtils.round(5.4, 0);
        assertEquals(5.0, result, "5.4 should round to 5.");

        result = NumberUtils.round(5.5, 0);
        assertEquals(6.0, result, "5.5 should round to 6.");

        result = NumberUtils.round(5.9, 0);
        assertEquals(6.0, result, "5.9 should round to 6.");

        result = NumberUtils.round(-5.5, 0);
        assertEquals(-5.0, result, "-5.5 should round to -5.0");

        result = NumberUtils.round(-5.4, 0);
        assertEquals(-5.0, result, "-5.4 should round to -5.");
    }

    @Test
    void testRoundWithOneDecimalPlace() {
        // Test rounding with precision 1 (rounding to one decimal place).
        double result = NumberUtils.round(5.44, 1);
        assertEquals(5.4, result, "5.44 should round to 5.4.");

        result = NumberUtils.round(5.45, 1);
        assertEquals(5.5, result, "5.45 should round to 5.5.");

        result = NumberUtils.round(-5.44, 1);
        assertEquals(-5.4, result, "-5.44 should round to -5.4.");

        result = NumberUtils.round(-5.45, 1);
        assertEquals(-5.5, result, "-5.45 should round to -5.5.");
    }

    @Test
    void testRoundWithTwoDecimalPlaces() {
        // Test rounding with precision 2 (rounding to two decimal places).
        double result = NumberUtils.round(5.445, 2);
        assertEquals(5.45, result, "5.445 should round to 5.45.");

        result = NumberUtils.round(5.446, 2);
        assertEquals(5.45, result, "5.446 should round to 5.45.");

        result = NumberUtils.round(-5.445, 2);
        assertEquals(-5.45, result, "-5.445 should round to -5.45.");

        result = NumberUtils.round(-5.446, 2);
        assertEquals(-5.45, result, "-5.446 should round to -5.45.");
    }

    @Test
    void testRoundWithLargeNumber() {
        // Test rounding large numbers with precision 3.
        double result = NumberUtils.round(123456.789123, 3);
        assertEquals(123456.789, result, "123456.789123 should round to 123456.789.");

        result = NumberUtils.round(-123456.789123, 3);
        assertEquals(-123456.789, result, "-123456.789123 should round to -123456.789.");
    }

    @Test
    void testRoundWithZeroPrecisionOnZero() {
        // Test when rounding zero with precision 0.
        double result = NumberUtils.round(0.0, 0);
        assertEquals(0.0, result, "0.0 should remain 0.0.");

        result = NumberUtils.round(-0.0, 0);
        assertEquals(0.0, result, "-0.0 should round to 0.0.");
    }

    @Test
    void testRoundEdgeCases2() {
        float result1 = NumberUtils.round(123.4567F);
        System.out.println(result1);

        // Edge case testing
        double result = NumberUtils.round(0.005, 2);  // Rounding to 2 decimal places
        assertEquals(0.01, result, "0.005 should round to 0.01.");

        result = NumberUtils.round(0.004, 2);  // Rounding to 2 decimal places
        assertEquals(0.0, result, "0.004 should round to 0.0.");

        result = NumberUtils.round(123.5555, 2);  // Rounding to 2 decimal places
        assertEquals(123.56, result, "123.5555 should round to 123.56.");

        result = NumberUtils.round(-123.5555, 2);  // Rounding to 2 decimal places
        assertEquals(-123.56, result, "-123.5555 should round to -123.56.");
    }
}
