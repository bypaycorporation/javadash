package io.javadash.NumberUtilsTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.javadash.NumberUtils;
import org.junit.jupiter.api.Test;

public class InRangeTest {
    // Test the inRange method with both start and end bounds
    @Test
    void testInRange() {
        // Test if 3 is between 2 (inclusive) and 4 (exclusive)
        assertTrue(NumberUtils.inRange(3, 2, 4), "3 should be between 2 (inclusive) and 4 (exclusive)");

        // Test if 4 is between 0 (inclusive) and 8 (exclusive)
        assertTrue(NumberUtils.inRange(4, 8), "4 should be between 0 (inclusive) and 8 (exclusive)");

        // Test if 4 is between 0 (inclusive) and 2 (exclusive)
        assertFalse(NumberUtils.inRange(4, 2), "4 should not be between 0 (inclusive) and 2 (exclusive)");

        // Test if 1.2 is between 0 (inclusive) and 2 (exclusive)
        assertTrue(NumberUtils.inRange(1.2, 2), "1.2 should be between 0 (inclusive) and 2 (exclusive)");

        // Test if 5.2 is between 0 (inclusive) and 5.5 (exclusive)
        assertTrue(NumberUtils.inRange(5.2, 5.5), "5.2 should be between 0 (inclusive) and 5.5 (exclusive)");
    }

    // Test the inRange method with swapped bounds (start > end)
    @Test
    void testInRangeBoundsSwap() {
        // Test if -3 is between -2 (inclusive) and -6 (exclusive), after swapping bounds
        assertTrue(NumberUtils.inRange(-3, -2, -6), "-3 should be between -2 (inclusive) and -6 (exclusive) after swapping bounds");

        // Test if 5 is between 10 (inclusive) and 2 (exclusive), after swapping bounds
        assertTrue(NumberUtils.inRange(5, 10, 2));
    }

    // Test the inRange method with default start (0) and only end bound
    @Test
    void testInRangeWithDefaultStart() {
        // Test if 3 is between 0 (inclusive) and 5 (exclusive)
        assertTrue(NumberUtils.inRange(3, 5), "3 should be between 0 (inclusive) and 5 (exclusive)");

        // Test if 0 is between 0 (inclusive) and 2 (exclusive)
        assertTrue(NumberUtils.inRange(0, 2), "0 should be between 0 (inclusive) and 2 (exclusive)");

        // Test if 5 is between 0 (inclusive) and 5 (exclusive)
        assertFalse(NumberUtils.inRange(5, 5), "5 should not be between 0 (inclusive) and 5 (exclusive)");

        // Test if -1 is between 0 (inclusive) and 3 (exclusive)
        assertFalse(NumberUtils.inRange(-1, 3), "-1 should not be between 0 (inclusive) and 3 (exclusive)");

        // Test if 2 is between 0 (inclusive) and 3 (exclusive)
        assertTrue(NumberUtils.inRange(2, 3), "2 should be between 0 (inclusive) and 3 (exclusive)");
    }

    // Test the inRange method with edge cases where number is exactly on bounds
    @Test
    void testInRangeEdgeCases() {
        // Test if 2 is between 2 (inclusive) and 2 (exclusive)
        assertFalse(NumberUtils.inRange(2, 2, 2), "2 should not be between 2 (inclusive) and 2 (exclusive)");

        // Test if 0 is between 0 (inclusive) and 0 (exclusive)
        assertFalse(NumberUtils.inRange(0, 0, 0), "0 should not be between 0 (inclusive) and 0 (exclusive)");

        // Test if 0 is between 0 (inclusive) and 2 (exclusive)
        assertTrue(NumberUtils.inRange(0, 0, 2), "0 should be between 0 (inclusive) and 2 (exclusive)");

        // Test if 0 is between 0 (inclusive) and -2 (exclusive), after swapping bounds
        assertFalse(NumberUtils.inRange(0, 0, -2));
    }
}
