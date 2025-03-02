package io.javadash.NumberUtilsTest;
import static org.junit.jupiter.api.Assertions.*;

import io.javadash.NumberUtils;
import org.junit.jupiter.api.Test;
public class AddTest {
    @Test
    void testAddIntegers() {
        assertEquals(9, NumberUtils.add(5, 4), "5 + 4 should equal 9");
        assertEquals(0, NumberUtils.add(5, null), "Adding null to 5 should return 0");
        assertEquals(0, NumberUtils.add(null, 5), "Adding 5 to null should return 0");
        Integer a = null;
        Integer b = null;
        assertEquals(0, NumberUtils.add(a, b), "Adding null and null should return 0");
    }

    // Test adding Floats
    @Test
    void testAddFloats() {
        assertEquals(9.0F, NumberUtils.add(5F, 4F), "5.0F + 4.0F should equal 9.0F");
        assertEquals(0.0F, NumberUtils.add(5F, null), "Adding null to 5.0F should return 0.0F");
        assertEquals(0.0F, NumberUtils.add(null, 5F), "Adding 5.0F to null should return 0.0F");
        Float a = null;
        Float b = null;
        assertEquals(0.0F, NumberUtils.add(a, b), "Adding null and null should return 0.0F");
    }

    // Test adding Doubles
    @Test
    void testAddDoubles() {
        assertEquals(9.0, NumberUtils.add(5.0, 4.0), "5.0 + 4.0 should equal 9.0");
        assertEquals(0.0, NumberUtils.add(5.0, null), "Adding null to 5.0 should return 0.0");
        assertEquals(0.0, NumberUtils.add(null, 5.0), "Adding 5.0 to null should return 0.0");
        Double a = null;
        Double b = null;
        assertEquals(0.0, NumberUtils.add(a, b), "Adding null and null should return 0.0");
    }

    // Test adding Longs
    @Test
    void testAddLongs() {
        assertEquals(9L, NumberUtils.add(5L, 4L), "5L + 4L should equal 9L");
        assertEquals(0L, NumberUtils.add(5L, null), "Adding null to 5L should return 0L");
        assertEquals(0L, NumberUtils.add(null, 5L), "Adding 5L to null should return 0L");
        Long a = null;
        Long b = null;
        assertEquals(0L, NumberUtils.add(a, b), "Adding null and null should return 0L");
    }

    // Test edge case: both numbers being the same
    @Test
    void testAddEdgeCase() {
        assertEquals(10, NumberUtils.add(5, 5), "5 + 5 should equal 10");
        assertEquals(10.0F, NumberUtils.add(5F, 5F), "5.0F + 5.0F should equal 10.0F");
        assertEquals(10.0, NumberUtils.add(5.0, 5.0), "5.0 + 5.0 should equal 10.0");
        assertEquals(10L, NumberUtils.add(5L, 5L), "5L + 5L should equal 10L");
    }

    // Test adding zero
    @Test
    void testAddZero() {
        assertEquals(5, NumberUtils.add(5, 0), "5 + 0 should equal 5");
        assertEquals(5.0F, NumberUtils.add(5F, 0F), "5.0F + 0.0F should equal 5.0F");
        assertEquals(5.0, NumberUtils.add(5.0, 0.0), "5.0 + 0.0 should equal 5.0");
        assertEquals(5L, NumberUtils.add(5L, 0L), "5L + 0L should equal 5L");
    }
}
