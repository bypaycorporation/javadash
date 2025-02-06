package io.javadash.StringUtilsTest;

import static io.javadash.StringUtils.startsWith;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StartsWithTest {

    @Test
    void testStartsWith_withValidStringAndTarget() {
        // Test for a string that starts with the target
        String input = "hello world";
        String target = "hello";
        assertTrue(startsWith(input, target));
    }

    @Test
    void testStartsWith_withStringNotStartingWithTarget() {
        // Test for a string that does not start with the target
        String input = "hello world";
        String target = "world";
        assertFalse(startsWith(input, target));
    }

    @Test
    void testStartsWith_withEmptyStringAndTarget() {
        // Test for an empty string and a target
        String input = "";
        String target = "hello";
        assertFalse(startsWith(input, target));
    }

    @Test
    void testStartsWith_withNullString() {
        // Test for a null string
        String input = null;
        String target = "hello";
        assertFalse(startsWith(input, target));
    }

    @Test
    void testStartsWith_withNullTarget() {
        // Test for a null target
        String input = "hello world";
        String target = null;
        assertFalse(startsWith(input, target));
    }

    @Test
    void testStartsWith_withBothNull() {
        // Test for both string and target being null
        String input = null;
        String target = null;
        assertFalse(startsWith(input, target));
    }

    @Test
    void testStartsWith_withTargetLongerThanString() {
        // Test for a target that is longer than the string
        String input = "hello";
        String target = "hello world";
        assertFalse(startsWith(input, target));
    }

    @Test
    void testStartsWith_withExactMatch() {
        // Test for an exact match where string equals target
        String input = "hello";
        String target = "hello";
        assertTrue(startsWith(input, target));
    }
    @Test
    void testStartsWith_withExactMatch1() {
        // Test for an exact match where string equals target
        String input = "hello";
        String target = "";
        assertTrue(startsWith(input, target));
    }
}

