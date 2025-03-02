package io.javadash.NumberUtilsTest;
import static org.junit.jupiter.api.Assertions.*;

import io.javadash.NumberUtils;
import org.junit.jupiter.api.Test;
public class SubtractTest {
    @Test
    void testSubtractIntegers() {
        assertEquals(1, NumberUtils.subtract(5, 4), "5 - 4 should equal 1");
        assertEquals(0, NumberUtils.subtract(5, null), "Subtracting null from 5 should return 0");
        assertEquals(0, NumberUtils.subtract(null, 5), "Subtracting 5 from null should return 0");
        Integer a = null;
        Integer b = null;
        assertEquals(0, NumberUtils.subtract(a, b), "Subtracting null from null should return 0");
    }

    // Test subtracting Floats
    @Test
    void testSubtractFloats() {
        assertEquals(1.0F, NumberUtils.subtract(5F, 4F), "5.0F - 4.0F should equal 1.0F");
        assertEquals(0.0F, NumberUtils.subtract(5F, null), "Subtracting null from 5.0F should return 0.0F");
        assertEquals(0.0F, NumberUtils.subtract(null, 5F), "Subtracting 5.0F from null should return 0.0F");
        Float a = null;
        Float b = null;
        assertEquals(0.0F, NumberUtils.subtract(a, b), "Subtracting null from null should return 0.0F");
    }

    // Test subtracting Doubles
    @Test
    void testSubtractDoubles() {
        assertEquals(1.0, NumberUtils.subtract(5.0, 4.0), "5.0 - 4.0 should equal 1.0");
        assertEquals(0.0, NumberUtils.subtract(5.0, null), "Subtracting null from 5.0 should return 0.0");
        assertEquals(0.0, NumberUtils.subtract(null, 5.0), "Subtracting 5.0 from null should return 0.0");
        Double a = null;
        Double b = null;
        assertEquals(0.0, NumberUtils.subtract(a, b), "Subtracting null from null should return 0.0");
    }

    // Test subtracting Longs
    @Test
    void testSubtractLongs() {
        assertEquals(1L, NumberUtils.subtract(5L, 4L), "5L - 4L should equal 1L");
        assertEquals(0L, NumberUtils.subtract(5L, null), "Subtracting null from 5L should return 0L");
        assertEquals(0L, NumberUtils.subtract(null, 5L), "Subtracting 5L from null should return 0L");
        Long a = null;
        Long b = null;
        assertEquals(0L, NumberUtils.subtract(a, b), "Subtracting null from null should return 0L");
    }

    // Test edge case: both numbers being the same
    @Test
    void testSubtractEdgeCase() {
        assertEquals(0, NumberUtils.subtract(5, 5), "5 - 5 should equal 0");
        assertEquals(0F, NumberUtils.subtract(5F, 5F), "5.0F - 5.0F should equal 0.0F");
        assertEquals(0.0, NumberUtils.subtract(5.0, 5.0), "5.0 - 5.0 should equal 0.0");
        assertEquals(0L, NumberUtils.subtract(5L, 5L), "5L - 5L should equal 0L");
    }

    // Test subtracting zero
    @Test
    void testSubtractZero() {
        assertEquals(5, NumberUtils.subtract(5, 0), "5 - 0 should equal 5");
        assertEquals(5.0F, NumberUtils.subtract(5F, 0F), "5.0F - 0.0F should equal 5.0F");
        assertEquals(5.0, NumberUtils.subtract(5.0, 0.0), "5.0 - 0.0 should equal 5.0");
        assertEquals(5L, NumberUtils.subtract(5L, 0L), "5L - 0L should equal 5L");
    }
}
