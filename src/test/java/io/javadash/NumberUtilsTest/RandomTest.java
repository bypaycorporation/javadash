package io.javadash.NumberUtilsTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.javadash.NumberUtils;
import org.junit.jupiter.api.Test;

public class RandomTest {
    // Test random integer generation between lower and upper bounds
    @Test
    void testRandomInt() {
        int lower = 0, upper = 5;
        int result = NumberUtils.random(lower, upper);

        // Assert that the result is within the expected range
        assertTrue(result >= lower && result <= upper, "Result should be between " + lower + " and " + upper);
    }

    // Test random integer generation where lower bound is greater than upper bound
    @Test
    void testRandomIntBoundsSwap() {
        int lower = 10, upper = 5;
        int result = NumberUtils.random(lower, upper);

        // Assert that the result is still within the correct range (5 to 10 after swapping)
        assertTrue(result >= 5 && result <= 10, "Result should be between 5 and 10 after swapping bounds");
    }

    // Test random long generation between lower and upper bounds
    @Test
    void testRandomLong() {
        long lower = 100L, upper = 200L;
        long result = NumberUtils.random(lower, upper);

        // Assert that the result is within the expected range
        assertTrue(result >= lower && result <= upper, "Result should be between " + lower + " and " + upper);
    }

    // Test random long generation where lower bound is greater than upper bound
    @Test
    void testRandomLongBoundsSwap() {
        long lower = 200L, upper = 100L;
        long result = NumberUtils.random(lower, upper);

        // Assert that the result is still within the correct range (100L to 200L after swapping)
        assertTrue(result >= 100L && result <= 200L, "Result should be between 100L and 200L after swapping bounds");
    }

    // Test random float generation between lower and upper bounds
    @Test
    void testRandomFloat() {
        float lower = 1.2F, upper = 5.2F;
        float result = NumberUtils.random(lower, upper);

        // Assert that the result is within the expected range
        assertTrue(result >= lower && result <= upper, "Result should be between " + lower + " and " + upper);
    }

    // Test random float generation where lower bound is greater than upper bound
    @Test
    void testRandomFloatBoundsSwap() {
        float lower = 5.2F, upper = 1.2F;
        float result = NumberUtils.random(lower, upper);

        // Assert that the result is still within the correct range (1.2F to 5.2F after swapping)
        assertTrue(result >= 1.2F && result <= 5.2F, "Result should be between 1.2F and 5.2F after swapping bounds");
    }

    // Test random double generation between lower and upper bounds
    @Test
    void testRandomDouble() {
        double lower = 1.5, upper = 5.5;
        double result = NumberUtils.random(lower, upper);

        // Assert that the result is within the expected range
        assertTrue(result >= lower && result <= upper, "Result should be between " + lower + " and " + upper);
    }

    // Test random double generation where lower bound is greater than upper bound
    @Test
    void testRandomDoubleBoundsSwap() {
        double lower = 5.5, upper = 1.5;
        double result = NumberUtils.random(lower, upper);

        // Assert that the result is still within the correct range (1.5 to 5.5 after swapping)
        assertTrue(result >= 1.5 && result <= 5.5, "Result should be between 1.5 and 5.5 after swapping bounds");
    }

    // Test random integer generation with a single upper bound
    @Test
    void testRandomIntSingleBound() {
        int upper = 10;
        int result = NumberUtils.random(upper);

        // Assert that the result is within the expected range (0 to 10)
        assertTrue(result >= 0 && result <= upper, "Result should be between 0 and " + upper);
    }

    // Test random long generation with a single upper bound
    @Test
    void testRandomLongSingleBound() {
        long upper = 100L;
        long result = NumberUtils.random(upper);

        // Assert that the result is within the expected range (0L to 100L)
        assertTrue(result >= 0L && result <= upper, "Result should be between 0L and " + upper);
    }

    // Test random float generation with a single upper bound
    @Test
    void testRandomFloatSingleBound() {
        float upper = 5.5F;
        float result = NumberUtils.random(upper);

        // Assert that the result is within the expected range (0.0F to 5.5F)
        assertTrue(result >= 0.0F && result <= upper, "Result should be between 0.0F and " + upper);
    }

    // Test random double generation with a single upper bound
    @Test
    void testRandomDoubleSingleBound() {
        double upper = 1.0;
        double result = NumberUtils.random(upper);

        // Assert that the result is within the expected range (0.0 to 1.0)
        assertTrue(result >= 0.0 && result <= upper, "Result should be between 0.0 and " + upper);
    }
}
