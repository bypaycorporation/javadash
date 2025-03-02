package io.javadash.NumberUtilsTest;
import static org.junit.jupiter.api.Assertions.*;

import io.javadash.NumberUtils;
import org.junit.jupiter.api.Test;
public class DivideTest {
    @Test
    void testDivideIntegers() {
        assertEquals(5, NumberUtils.divide(10, 2), "10 / 2 should equal 5");
        assertEquals(0, NumberUtils.divide(10, null), "Dividing by null should return 0");
        assertEquals(0, NumberUtils.divide(null, 2), "Dividing null by 2 should return 0");
        assertEquals(0, NumberUtils.divide(10, 0), "Dividing by 0 should return 0");
        Integer a = null;
        Integer b = null;
        assertEquals(0, NumberUtils.divide(a, b), "Dividing null by null should return 0");
    }

    // Test dividing Floats
    @Test
    void testDivideFloats() {
        assertEquals(5.0F, NumberUtils.divide(10F, 2F), "10.0F / 2.0F should equal 5.0F");
        assertEquals(0.0F, NumberUtils.divide(10F, null), "Dividing by null should return 0.0F");
        assertEquals(0.0F, NumberUtils.divide(null, 2F), "Dividing null by 2.0F should return 0.0F");
        assertEquals(0.0F, NumberUtils.divide(10F, 0F), "Dividing by 0.0F should return 0.0F");
        Float a = null;
        Float b = null;
        assertEquals(0.0F, NumberUtils.divide(a, b), "Dividing null by null should return 0.0F");
    }

    // Test dividing Doubles
    @Test
    void testDivideDoubles() {
        assertEquals(5.0, NumberUtils.divide(10.0, 2.0), "10.0 / 2.0 should equal 5.0");
        assertEquals(0.0, NumberUtils.divide(10.0, null), "Dividing by null should return 0.0");
        assertEquals(0.0, NumberUtils.divide(null, 2.0), "Dividing null by 2.0 should return 0.0");
        assertEquals(0.0, NumberUtils.divide(10.0, 0.0), "Dividing by 0.0 should return 0.0");
        Double a = null;
        Double b = null;
        assertEquals(0.0, NumberUtils.divide(a, b), "Dividing null by null should return 0.0");
    }

    // Test dividing Longs
    @Test
    void testDivideLongs() {
        assertEquals(5L, NumberUtils.divide(10L, 2L), "10L / 2L should equal 5L");
        assertEquals(0L, NumberUtils.divide(10L, null), "Dividing by null should return 0L");
        assertEquals(0L, NumberUtils.divide(null, 2L), "Dividing null by 2L should return 0L");
        assertEquals(0L, NumberUtils.divide(10L, 0L), "Dividing by 0L should return 0L");
        Long a = null;
        Long b = null;
        assertEquals(0L, NumberUtils.divide(a, b), "Dividing null by null should return 0L");
    }

    // Test edge case: dividing a number by itself (should return 1)
    @Test
    void testDivideEdgeCase() {
        assertEquals(1, NumberUtils.divide(10, 10), "10 / 10 should equal 1");
        assertEquals(1.0F, NumberUtils.divide(10F, 10F), "10.0F / 10.0F should equal 1.0F");
        assertEquals(1.0, NumberUtils.divide(10.0, 10.0), "10.0 / 10.0 should equal 1.0");
        assertEquals(1L, NumberUtils.divide(10L, 10L), "10L / 10L should equal 1L");
    }

    // Test dividing by zero edge case
    @Test
    void testDivideByZero() {
        assertEquals(0, NumberUtils.divide(10, 0), "Dividing 10 by 0 should return 0");
        assertEquals(0.0F, NumberUtils.divide(10F, 0F), "Dividing 10.0F by 0.0F should return 0.0F");
        assertEquals(0.0, NumberUtils.divide(10.0, 0.0), "Dividing 10.0 by 0.0 should return 0.0");
        assertEquals(0L, NumberUtils.divide(10L, 0L), "Dividing 10L by 0L should return 0L");
    }

    // Test dividing by null
    @Test
    void testDivideByNull() {
        assertEquals(0, NumberUtils.divide(10, null), "Dividing by null should return 0");
        assertEquals(0.0F, NumberUtils.divide(10F, null), "Dividing by null should return 0.0F");
        assertEquals(0.0, NumberUtils.divide(10.0, null), "Dividing by null should return 0.0");
        assertEquals(0L, NumberUtils.divide(10L, null), "Dividing by null should return 0L");
    }

    // Test dividing null by a valid number
    @Test
    void testDivideNullByValid() {
        assertEquals(0, NumberUtils.divide(null, 10), "Dividing null by 10 should return 0");
        assertEquals(0.0F, NumberUtils.divide(null, 10F), "Dividing null by 10.0F should return 0.0F");
        assertEquals(0.0, NumberUtils.divide(null, 10.0), "Dividing null by 10.0 should return 0.0");
        assertEquals(0L, NumberUtils.divide(null, 10L), "Dividing null by 10L should return 0L");
    }
}
