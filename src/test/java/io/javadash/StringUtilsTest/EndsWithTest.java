package io.javadash.StringUtilsTest;

import static io.javadash.StringUtils.endsWith;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class EndsWithTest {

    @Test
    void testEndsWithWithValidStringAndTarget() {
        // Test when the string ends with the target string
        assertTrue(endsWith("HelloWorld", "World"), "String should end with the target.");
    }

    @Test
    void testEndsWithWithValidStringAndTargetMismatch() {
        // Test when the string does not end with the target string
        assertFalse(endsWith("HelloWorld", "Hello"), "String should not end with the target.");
    }

    @Test
    void testEndsWithWithEmptyString() {
        // Test when the string is empty and the target is non-empty
        assertFalse(endsWith("", "Hello"), "Empty string should not end with a non-empty target.");
    }

    @Test
    void testEndsWithWithEmptyTarget() {
        // Test when the target is empty and the string is non-empty
        assertFalse(endsWith("Hello", ""), "Non-empty string should always end with an empty target.");
    }

    @Test
    void testEndsWithWithNullString() {
        // Test when the string is null
        assertFalse(endsWith(null, "Hello"), "Null string should not end with any target.");
    }

    @Test
    void testEndsWithWithNullTarget() {
        // Test when the target is null
        assertFalse(endsWith("Hello", null), "String should not end with a null target.");
    }

    @Test
    void testEndsWithWithBothEmpty() {
        // Test when both the string and target are empty
        assertFalse(endsWith("", ""), "Empty string should end with an empty target.");
    }

    @Test
    void testEndsWithWithStringEqualsTarget() {
        // Test when the string is equal to the target
        assertTrue(endsWith("Hello", "Hello"), "String should end with the target when both are equal.");
    }
}

